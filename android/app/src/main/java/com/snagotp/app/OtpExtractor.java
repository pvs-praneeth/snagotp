package com.snagotp.app;

import android.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for extracting OTP (One-Time Password) from SMS message text
 * Uses multiple regex patterns to match various OTP formats
 * Contributors: Add more regex patterns below to support additional OTP formats
 */
public class OtpExtractor {
    private static final String TAG = "OtpExtractor";
    
    // Array of regex patterns to match different OTP formats
    // Pattern priority: More specific patterns should come first
    private static final Pattern[] OTP_PATTERNS = {
        // Pattern 1: 4-8 digit OTP with keywords like "OTP", "code", "verification"
        // Example: "Your OTP is 123456" or "Verification code: 1234"
        Pattern.compile("(?:OTP|otp|code|verification|verify|pin|passcode)[^0-9]*([0-9]{4,8})", Pattern.CASE_INSENSITIVE),
        
        // Pattern 2: Standalone 4-8 digit number (more generic)
        // Example: "123456 is your code" or "Use 1234 to login"
        Pattern.compile("\\b([0-9]{4,8})\\b"),
        
        // Pattern 3: Alphanumeric OTP (4-8 characters)
        // Example: "Your code is A1B2C3"
        Pattern.compile("(?:OTP|otp|code|verification|verify)[^A-Za-z0-9]*([A-Za-z0-9]{4,8})", Pattern.CASE_INSENSITIVE),
        
        // Pattern 4: OTP with hyphen or space separation
        // Example: "123-456" or "123 456"
        Pattern.compile("\\b([0-9]{3}[\\s-][0-9]{3})\\b"),
        
        // Pattern 5: 6-digit OTP at the start of message
        // Example: "123456 is your verification code"
        Pattern.compile("^([0-9]{6})")
    };

    /**
     * Extract OTP from the given message text
     * Tries multiple regex patterns in order of priority
     * 
     * @param messageText The SMS message body to extract OTP from
     * @return The extracted OTP string, or null if no OTP found
     */
    public static String extractOtp(String messageText) {
        if (messageText == null || messageText.trim().isEmpty()) {
            Log.w(TAG, "Message text is null or empty");
            return null;
        }

        Log.d(TAG, "Attempting to extract OTP from message: " + messageText);

        // Try each pattern in order
        for (int i = 0; i < OTP_PATTERNS.length; i++) {
            Pattern pattern = OTP_PATTERNS[i];
            Matcher matcher = pattern.matcher(messageText);
            
            if (matcher.find()) {
                String otp = matcher.group(1);
                if (otp != null && !otp.isEmpty()) {
                    // Remove any spaces or hyphens from the OTP
                    otp = otp.replaceAll("[\\s-]", "");
                    
                    Log.i(TAG, "OTP extracted using pattern " + (i + 1) + ": " + otp);
                    return otp;
                }
            }
        }

        Log.w(TAG, "No OTP found in message");
        return null;
    }

    /**
     * Validate if the extracted string is a valid OTP
     * Can be extended with additional validation logic
     * 
     * @param otp The OTP string to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidOtp(String otp) {
        if (otp == null || otp.isEmpty()) {
            return false;
        }
        
        // Basic validation: OTP should be 4-8 characters
        int length = otp.length();
        if (length < 4 || length > 8) {
            Log.w(TAG, "OTP length invalid: " + length);
            return false;
        }
        
        // OTP should contain at least one digit
        if (!otp.matches(".*[0-9].*")) {
            Log.w(TAG, "OTP does not contain digits");
            return false;
        }
        
        return true;
    }

    /**
     * Extract OTP with validation
     * Combines extraction and validation in one method
     * 
     * @param messageText The SMS message body
     * @return Validated OTP string, or null if invalid
     */
    public static String extractAndValidateOtp(String messageText) {
        String otp = extractOtp(messageText);
        
        if (otp != null && isValidOtp(otp)) {
            return otp;
        }
        
        Log.w(TAG, "Extracted OTP failed validation");
        return null;
    }
}
