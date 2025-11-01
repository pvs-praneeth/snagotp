package com.snagotp.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;

/**
 * Helper class for clipboard operations
 * Provides methods to copy text (OTP) to system clipboard
 * Contributors: Extend with additional clipboard functionality as needed
 */
public class ClipboardHelper {
    private static final String TAG = "ClipboardHelper";
    private static final String CLIP_LABEL = "OTP";

    /**
     * Copy text to the system clipboard
     * 
     * @param context Application context
     * @param text Text to copy (OTP code)
     * @return true if successfully copied, false otherwise
     */
    public static boolean copyToClipboard(Context context, String text) {
        // Validate inputs
        if (context == null) {
            Log.e(TAG, "Context is null, cannot copy to clipboard");
            return false;
        }

        if (text == null || text.isEmpty()) {
            Log.e(TAG, "Text is null or empty, nothing to copy");
            return false;
        }

        try {
            // Get clipboard manager
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            
            if (clipboardManager == null) {
                Log.e(TAG, "ClipboardManager is null");
                return false;
            }

            // Create clip data with the OTP text
            ClipData clipData = ClipData.newPlainText(CLIP_LABEL, text);
            
            // Set clip data to clipboard
            clipboardManager.setPrimaryClip(clipData);
            
            Log.i(TAG, "Text copied to clipboard: " + text);
            return true;

        } catch (Exception e) {
            Log.e(TAG, "Error copying to clipboard: " + e.getMessage(), e);
            return false;
        }
    }

    /**
     * Get text from the system clipboard
     * Useful for testing or verification purposes
     * 
     * @param context Application context
     * @return Text from clipboard, or null if empty/error
     */
    public static String getFromClipboard(Context context) {
        if (context == null) {
            Log.e(TAG, "Context is null, cannot get from clipboard");
            return null;
        }

        try {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            
            if (clipboardManager == null || !clipboardManager.hasPrimaryClip()) {
                Log.d(TAG, "Clipboard is empty");
                return null;
            }

            ClipData clipData = clipboardManager.getPrimaryClip();
            if (clipData == null || clipData.getItemCount() == 0) {
                Log.d(TAG, "No clip data available");
                return null;
            }

            ClipData.Item item = clipData.getItemAt(0);
            CharSequence text = item.getText();
            
            if (text != null) {
                Log.d(TAG, "Retrieved text from clipboard");
                return text.toString();
            }

        } catch (Exception e) {
            Log.e(TAG, "Error getting from clipboard: " + e.getMessage(), e);
        }

        return null;
    }

    /**
     * Clear the clipboard
     * Can be used for security purposes to remove sensitive OTP data
     * 
     * @param context Application context
     * @return true if successfully cleared, false otherwise
     */
    public static boolean clearClipboard(Context context) {
        if (context == null) {
            Log.e(TAG, "Context is null, cannot clear clipboard");
            return false;
        }

        try {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            
            if (clipboardManager == null) {
                Log.e(TAG, "ClipboardManager is null");
                return false;
            }

            // Clear clipboard by setting empty clip data
            ClipData clipData = ClipData.newPlainText("", "");
            clipboardManager.setPrimaryClip(clipData);
            
            Log.i(TAG, "Clipboard cleared");
            return true;

        } catch (Exception e) {
            Log.e(TAG, "Error clearing clipboard: " + e.getMessage(), e);
            return false;
        }
    }

    /**
     * Check if clipboard has content
     * 
     * @param context Application context
     * @return true if clipboard has content, false otherwise
     */
    public static boolean hasClipboardContent(Context context) {
        if (context == null) {
            return false;
        }

        try {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            return clipboardManager != null && clipboardManager.hasPrimaryClip();
        } catch (Exception e) {
            Log.e(TAG, "Error checking clipboard content: " + e.getMessage(), e);
            return false;
        }
    }
}
