package com.snagotp.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * BroadcastReceiver to listen for incoming SMS/RCS messages
 * Extracts OTP from message body and copies to clipboard
 * Contributors: Add additional OTP patterns or custom logic as needed
 */
public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = "SmsReceiver";
    private static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            // Check if the intent action is SMS received
            if (intent.getAction() == null || !intent.getAction().equals(SMS_RECEIVED_ACTION)) {
                Log.d(TAG, "Non-SMS intent received, ignoring");
                return;
            }

            // Extract SMS messages from intent
            Bundle bundle = intent.getExtras();
            if (bundle == null) {
                Log.e(TAG, "Bundle is null, cannot process SMS");
                return;
            }

            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus == null || pdus.length == 0) {
                Log.e(TAG, "No PDUs found in bundle");
                return;
            }

            // Parse SMS messages
            StringBuilder messageBody = new StringBuilder();
            for (Object pdu : pdus) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                if (smsMessage != null) {
                    messageBody.append(smsMessage.getMessageBody());
                }
            }

            String fullMessage = messageBody.toString();
            Log.d(TAG, "SMS received: " + fullMessage);

            // Extract OTP from message
            String otp = OtpExtractor.extractOtp(fullMessage);
            
            if (otp != null && !otp.isEmpty()) {
                Log.i(TAG, "OTP detected: " + otp);
                
                // Copy OTP to clipboard
                boolean copied = ClipboardHelper.copyToClipboard(context, otp);
                
                if (copied) {
                    // Show toast notification to user
                    Toast.makeText(context, "OTP copied to clipboard: " + otp, Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "OTP successfully copied to clipboard");
                } else {
                    Log.e(TAG, "Failed to copy OTP to clipboard");
                }
            } else {
                Log.d(TAG, "No OTP found in message");
            }

        } catch (Exception e) {
            Log.e(TAG, "Error processing SMS: " + e.getMessage(), e);
            // Don't crash the app, just log the error
        }
    }
}
