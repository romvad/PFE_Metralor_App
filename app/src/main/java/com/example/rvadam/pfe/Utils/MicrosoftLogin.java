package com.example.rvadam.pfe.Utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.rvadam.pfe.Model.Constants;
import com.microsoft.identity.client.AuthenticationCallback;
import com.microsoft.identity.client.AuthenticationResult;
import com.microsoft.identity.client.MsalClientException;
import com.microsoft.identity.client.MsalException;
import com.microsoft.identity.client.MsalServiceException;
import com.microsoft.identity.client.MsalUiRequiredException;
import com.microsoft.identity.client.PublicClientApplication;
import com.microsoft.identity.client.User;

import java.util.List;

/**
 * Created by rvadam on 04/05/2018.
 */

public class MicrosoftLogin {

    private static final String TAG = "MicrosoftLogin";

    /* Azure AD Variables */
    private PublicClientApplication sampleApp;
    private AuthenticationResult authResult;

    Constants constantsInstance = Constants.getInstance();

    public  PublicClientApplication microsoftAuthentication(AppCompatActivity activityCalling){
        /* Configure your sample app and save state for this activity */
        sampleApp = null;
        if (sampleApp == null) {
            sampleApp = new PublicClientApplication(
                    activityCalling.getApplicationContext(),
                    constantsInstance.getOneDriveClientId());
        }

/* Attempt to get a user and acquireTokenSilent
* If this fails we do an interactive request
*/
        List<User> users = null;    try {
            users = sampleApp.getUsers();

            if (users != null && users.size() == 1) {
    /* We have 1 user */
                Log.i(TAG,"acquireTokenSilentAsync call");//deja co
                sampleApp.acquireTokenSilentAsync(constantsInstance.getOneDriveScopes(), users.get(0), getAuthSilentCallback());
            } else {
    /* We have no user */

    /* Let's do an interactive request */
                Log.i(TAG,"acquireToken call");//
                sampleApp.acquireToken(activityCalling, constantsInstance.getOneDriveScopes(), getAuthInteractiveCallback());
            }
        } catch (MsalClientException e) {
            Log.d(TAG, "MSAL Exception Generated while getting users: " + e.toString());

        } catch (IndexOutOfBoundsException e) {
            Log.d(TAG, "User at this position does not exist: " + e.toString());
        }

        return sampleApp;
    }

    /* Callback method for acquireTokenSilent calls
    * Looks if tokens are in the cache (refreshes if necessary and if we don't forceRefresh)
    * else errors that we need to do an interactive request.
    */
    private AuthenticationCallback getAuthSilentCallback() {
        Log.i(TAG, "getAuthSilentCallback call");//deja co
        return new AuthenticationCallback() {
            @Override
            public void onSuccess(AuthenticationResult authenticationResult) {
        /* Successfully got a token, call Graph now */
                Log.d(TAG, "Successfully authenticated");

        /* Store the authResult */
                authResult = authenticationResult;
            }

            @Override
            public void onError(MsalException exception) {
                /* Failed to acquireToken */
                Log.d(TAG, "Authentication failed: " + exception.toString());

                if (exception instanceof MsalClientException) {
            /* Exception inside MSAL, more info inside MsalError.java */
                } else if (exception instanceof MsalServiceException) {
            /* Exception when communicating with the STS, likely config issue */
                } else if (exception instanceof MsalUiRequiredException) {
            /* Tokens expired or no session, retry with interactive */
                }
            }

            @Override
            public void onCancel() {
                /* User cancelled the authentication */
                Log.d(TAG, "User cancelled login.");
            }


        };
    }

    /* Callback used for interactive request.  If succeeds we use the access
        * token to call the Microsoft Graph. Does not check cache
        */
    private AuthenticationCallback getAuthInteractiveCallback() {
        Log.i(TAG,"getAuthInteractiveCallback call");//login frame called
        return new AuthenticationCallback() {
            @Override
            public void onSuccess(AuthenticationResult authenticationResult) {
        /* Successfully got a token, call graph now */
                Log.d(TAG, "Successfully authenticated");
                Log.d(TAG, "ID Token: " + authenticationResult.getIdToken());

        /* Store the auth result */
                authResult = authenticationResult;

            }

            @Override
            public void onError(MsalException exception) {
        /* Failed to acquireToken */
                Log.d(TAG, "Authentication failed: " + exception.toString());

                if (exception instanceof MsalClientException) {
            /* Exception inside MSAL, more info inside MsalError.java */
                } else if (exception instanceof MsalServiceException) {
            /* Exception when communicating with the STS, likely config issue */
                }
            }

            @Override
            public void onCancel() {
        /* User cancelled the authentication */
                Log.d(TAG, "User cancelled login.");
            }
        };
    }

    /* Handles the redirect from the System Browser */
   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG,"onActivityResult call");// end of login frame
        sampleApp.handleInteractiveRequestRedirect(requestCode, resultCode, data);
    }*/

   public void signOut(){

       /* Attempt to get a user and remove their cookies from cache */
       List<User> users = null;

       try {
           users = sampleApp.getUsers();

           if (users == null) {
            /* We have no users */

           } else if (users.size() == 1) {
            /* We have 1 user */
            /* Remove from token cache */
               sampleApp.remove(users.get(0));
               //updateSignedOutUI();

           }
           else {
            /* We have multiple users */
               for (int i = 0; i < users.size(); i++) {
                   sampleApp.remove(users.get(i));
               }
           }

           //Toast.makeText(activityCalling.getBaseContext(), "Signed Out!", Toast.LENGTH_SHORT)
             //      .show();

       } catch (MsalClientException e) {
           Log.d(TAG, "MSAL Exception Generated while getting users: " + e.toString());

       } catch (IndexOutOfBoundsException e) {
           Log.d(TAG, "User at this position does not exist: " + e.toString());
       }
   }
}

