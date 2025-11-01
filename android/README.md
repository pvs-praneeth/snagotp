# Android App - SNAGOTP

This folder contains the Android application for SNAGOTP (Secure Native Application for Google OTP).

## Project Overview

The Android app provides native mobile access to SNAGOTP functionality with a focus on security, usability, and modern Android development practices.

## Technical Specifications

### Preferred Language
- **Java** (Primary development language)
- Follow Java best practices and Android coding standards
- Compatible with modern Android development tools

### Minimum Requirements
- **Minimum SDK Version:** 21 (Android 5.0 Lollipop)
- **Target SDK:** Latest stable version
- **Compile SDK:** Latest stable version
- Ensures compatibility with 95%+ of active Android devices

### UI Framework
- **Material Design 3** (Material You)
- Dynamic color theming support
- Consistent with Android 12+ design guidelines
- Adaptive layouts for different screen sizes
- Dark mode support

## Project Structure

```
/android/
├── app/                    # Main application source code
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/      # Java source files
│   │   │   ├── res/       # Resources (layouts, drawables, etc.)
│   │   │   └── AndroidManifest.xml
│   │   ├── test/          # Unit tests
│   │   └── androidTest/   # Instrumentation tests
│   └── build.gradle       # App-level build configuration
├── docs/                   # Architecture and integration documentation
│   ├── architecture.md    # System architecture details (TBD)
│   ├── integration.md     # Backend integration guide (TBD)
│   └── ui-guidelines.md   # UI/UX guidelines (TBD)
└── README.md              # This file
```

## Getting Started

### Prerequisites
- Android Studio (Latest stable version recommended)
- JDK 11 or higher
- Android SDK with API 21+ installed
- Gradle 7.0+ (usually bundled with Android Studio)

### Build Instructions

1. **Clone the repository:**
   ```bash
   git clone https://github.com/pvs-praneeth/snagotp.git
   cd snagotp/android
   ```

2. **Open in Android Studio:**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the `android` directory
   - Wait for Gradle sync to complete

3. **Configure the project:**
   - Ensure SDK 21+ is installed via SDK Manager
   - Sync project with Gradle files
   - Resolve any dependency issues

4. **Build the app:**
   ```bash
   ./gradlew assembleDebug
   ```

5. **Run on device/emulator:**
   - Connect an Android device or start an emulator
   - Click the "Run" button in Android Studio
   - Or use: `./gradlew installDebug`

### Testing

```bash
# Run unit tests
./gradlew test

# Run instrumentation tests
./gradlew connectedAndroidTest

# Generate test coverage report
./gradlew jacocoTestReport
```

## Required Permissions

The following permissions will be required by the app:

- **INTERNET** - For network communication with backend services
- **CAMERA** (optional) - For QR code scanning functionality
- **BIOMETRIC** (optional) - For biometric authentication
- *Additional permissions TBD based on feature requirements*

### Runtime Permissions
Permissions will be requested at runtime following Android best practices:
- Clear explanation of why each permission is needed
- Graceful degradation if permission is denied
- Option to grant permissions later from settings

## Feature Planning

### Phase 1 - Core Functionality (Priority)
- [ ] User authentication and authorization
- [ ] OTP generation and management
- [ ] Secure storage of credentials
- [ ] Basic Material 3 UI implementation
- [ ] Settings and preferences

### Phase 2 - Enhanced Features
- [ ] QR code scanning for setup
- [ ] Biometric authentication
- [ ] Backup and restore functionality
- [ ] Multi-account support
- [ ] Widget support

### Phase 3 - Advanced Features
- [ ] Wear OS companion app
- [ ] Auto-fill integration
- [ ] Cloud sync capabilities
- [ ] Advanced security features (anti-tampering, etc.)

## Onboarding Instructions

### For New Developers

1. **Familiarize yourself with the codebase:**
   - Review the project structure above
   - Read documentation in `/docs` directory
   - Understand the Material 3 design system

2. **Set up your development environment:**
   - Install Android Studio (latest stable)
   - Configure Java SDK (JDK 11+)
   - Install required Android SDK components
   - Set up Git and configure your credentials

3. **Understand the tech stack:**
   - **Language:** Java
   - **UI Framework:** Material Design 3
   - **Build System:** Gradle
   - **Architecture Pattern:** TBD (likely MVVM or Clean Architecture)
   - **Testing:** JUnit, Espresso, Mockito

4. **Coding standards:**
   - Follow Android code style guidelines
   - Use meaningful variable and method names
   - Comment complex logic and public APIs
   - Write unit tests for business logic
   - Write UI tests for critical user flows

5. **Development workflow:**
   - Create a feature branch from `master`
   - Make small, focused commits with clear messages
   - Test thoroughly before pushing
   - Create pull requests for code review
   - Address review feedback promptly

6. **Resources:**
   - [Android Developer Documentation](https://developer.android.com/)
   - [Material Design 3](https://m3.material.io/)
   - [Java Documentation](https://docs.oracle.com/en/java/)
   - Project-specific docs in `/docs` directory

### First Tasks for New Contributors

1. Set up the development environment
2. Build and run the app successfully
3. Explore the existing codebase
4. Pick up a "good first issue" from the issue tracker
5. Review and understand the contribution guidelines

## Architecture Documentation

Detailed architecture documentation will be maintained in the `/docs` directory:

- **architecture.md** - Overall system design and component interactions
- **integration.md** - Backend API integration specifications
- **ui-guidelines.md** - UI/UX standards and component usage

## Security Considerations

- All sensitive data must be encrypted at rest
- Use Android Keystore for key management
- Implement certificate pinning for network requests
- Follow OWASP Mobile Security guidelines
- Regular security audits and dependency updates

## Contributing

Please follow the project's contribution guidelines:

1. Fork the repository
2. Create a feature branch
3. Make your changes with clear commit messages
4. Write/update tests as needed
5. Submit a pull request with a clear description
6. Respond to code review feedback

## License

This project is licensed under the MIT License - see the LICENSE file in the root directory for details.

## Contact & Support

For questions, issues, or feature requests:
- Open an issue on GitHub
- Contact the project maintainer: pvs-praneeth

---

*Last Updated: October 31, 2025*
*Status: Initial Setup Phase*
