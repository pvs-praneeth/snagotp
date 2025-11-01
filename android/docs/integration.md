# Integration Guide

## SNAGOTP Android App Integration

> Status: Draft - To Be Developed
> Last Updated: October 31, 2025

---

## Overview

This document describes how the SNAGOTP Android app integrates with backend services, third-party libraries, and platform services. It covers API specifications, authentication, error handling, and environment configuration.

## Backend API Integration

### Base URL
- Production: https://api.snagotp.com (TBD)
- Staging: https://staging.api.snagotp.com (TBD)

### Authentication
- Bearer token authentication
- OAuth 2.0 / OpenID Connect (TBD by backend)
- Token storage using Android Keystore-backed encryption

### Common Headers
- Authorization: Bearer <token>
- Content-Type: application/json
- Accept: application/json

### Endpoints (Example)

1. GET /accounts
   - Description: Retrieves a list of OTP accounts
   - Response: 200 OK, JSON array of accounts

2. POST /otp/generate
   - Description: Generates a one-time password for a given account
   - Request Body: { "accountId": "string" }
   - Response: 200 OK, { "otp": "string", "expiresIn": 30 }

3. POST /auth/login
   - Description: Authenticates a user and returns access/refresh tokens
   - Request Body: { "username": "string", "password": "string" }
   - Response: 200 OK, { "accessToken": "string", "refreshToken": "string" }

### Error Handling
- Standardized error format: { "code": "string", "message": "string", "details": {} }
- HTTP status codes:
  - 200-299: Success
  - 400: Bad Request
  - 401: Unauthorized
  - 403: Forbidden
  - 404: Not Found
  - 500: Internal Server Error
- Retry logic with exponential backoff for transient errors (5xx)

## Network Layer Implementation (Java)

- Retrofit for API calls
- OkHttp for HTTP client with interceptors (logging, auth)
- Gson/Moshi for JSON serialization

```java
OkHttpClient client = new OkHttpClient.Builder()
    .addInterceptor(new HttpLoggingInterceptor().setLevel(Level.BODY))
    .addInterceptor(chain -> {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json");
        // Add auth header if available
        String token = tokenProvider.getAccessToken();
        if (token != null) {
            requestBuilder.header("Authorization", "Bearer " + token);
        }
        return chain.proceed(requestBuilder.build());
    })
    .build();

Retrofit retrofit = new Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build();
```

## Environment Configuration

Use BuildConfig fields or Gradle build types for environment-specific values.

```gradle
// app/build.gradle
android {
    buildTypes {
        debug {
            buildConfigField "String", "BASE_URL", '"https://staging.api.snagotp.com"'
        }
        release {
            buildConfigField "String", "BASE_URL", '"https://api.snagotp.com"'
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}
```

## Token Management

- Securely store tokens using EncryptedSharedPreferences
- Refresh tokens using a background job when nearing expiration
- Handle token revocation and logout properly

```java
EncryptedSharedPreferences prefs = EncryptedSharedPreferences.create(
    context,
    "secure_prefs",
    masterKey,
    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
);
```

## Third-Party Integrations

- Analytics: Firebase Analytics (TBD)
- Crash Reporting: Firebase Crashlytics (TBD)
- QR Scanning: ZXing / ML Kit (TBD)

## Platform Services

- Biometric authentication API
- Camera API for QR scanning
- WorkManager for background tasks (token refresh, sync)

## Logging & Monitoring

- Use Timber or Android Log for structured logs
- Sensitive data must never be logged
- Enable verbose logging only in debug builds

## Security Considerations

- Certificate pinning using OkHttp CertificatePinner
- Strict HTTPS
- Input validation and output encoding
- Handle edge cases: clock skew, network timeouts, offline mode

## Testing Integration

- Use MockWebServer for API integration tests
- Dependency injection for testable network layer
- Write end-to-end tests for critical flows

## Release Checklist

- [ ] Update BASE_URL and environment configs
- [ ] Verify token management and refresh flows
- [ ] Run full test suite (unit, integration, UI)
- [ ] Validate permissions and privacy policy updates
- [ ] Perform security review

---

Contributions to this document are welcome. Please update as integration details evolve.
