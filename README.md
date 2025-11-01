# SnagOTP

SnagOTP is an open-source Android app that automatically reads, parses, and manages One-Time Passwords (OTPs) received from other companies via RCS/Google Messages. Built with a modern Material 3 UI, SnagOTP makes authenticating with OTPs faster and more convenient.

## Repository Structure

This repository is organized into distinct folders for different components of the SNAGOTP project:

```
snagotp/
├── android/          # Android mobile application
├── website/          # Web application
├── backend/          # (Planned) Backend services and APIs
├── docs/             # (Planned) Additional documentation
└── README.md         # This file
```

### Current Components

- **`/android`** - Contains the Android mobile application. See [android/README.md](android/README.md) for details.
- **`/website`** - Contains the web application. See [website/README.md](website/README.md) for details.

### Future Components

- **`/backend`** - Backend services, APIs, and server-side logic (to be added)
- **`/docs`** - Comprehensive documentation, guides, and specifications (to be added)
- **`/shared`** - Shared libraries and common code (if needed)

*Note: For specific setup requirements and implementation details for each component, please consult with the project owner.*

## Purpose

SnagOTP aims to streamline the two-factor authentication (2FA) experience by automatically extracting OTP codes from incoming messages and making them instantly accessible. No more manual copying or switching between apps—SnagOTP handles OTP detection and clipboard management seamlessly.

## Main Features

- **Automatic OTP Detection**: Automatically reads and parses OTP codes from RCS/Google Messages
- **Instant Clipboard Copy**: Copies extracted OTPs to clipboard for quick pasting (e.g., via Gboard)
- **OTP History & Statistics**: View all received OTPs and track authentication patterns
- **RCS/Google Messages Integration**: Works seamlessly with your existing messaging app
- **Material 3 Design**: Modern, beautiful UI following latest Material Design guidelines
- **Open Source**: Fully transparent codebase available for community review and contributions
- **Privacy-Focused**: Processes messages locally on your device
- **Multi-Account Support**: Manage OTPs from multiple services and accounts

## How It Works

1. **Receive**: SnagOTP monitors incoming RCS/Google Messages for OTP codes
2. **Extract**: Automatically parses and identifies the OTP from the message
3. **Copy**: Places the OTP on your clipboard, ready for instant pasting
4. **Track**: Stores OTP history and provides usage statistics

## Getting Started

```bash
# Clone the repository
git clone https://github.com/pvs-praneeth/snagotp.git
cd snagotp

# Navigate to the component you want to work on
cd android/   # For Android app
# or
cd website/   # For web application

# Build and install instructions will be added in each component's README
```

## Why SnagOTP?

SnagOTP is designed for users who value:

- **Speed**: No more manual OTP copying from messages
- **Convenience**: Automatic clipboard integration for seamless pasting
- **Transparency**: Open-source code that you can audit and trust
- **Privacy**: All OTP processing happens locally on your device
- **Modern Design**: Beautiful Material 3 UI that feels native to Android

## How to Contribute

We welcome contributions from the community! SnagOTP is built collaboratively, and your ideas and improvements are valued.

### Ways to Contribute

1. **Fork the Repository**: Click the 'Fork' button at the top right of this page
2. **Clone Your Fork**: 
   ```bash
   git clone https://github.com/your-username/snagotp.git
   cd snagotp
   ```
3. **Create a Branch**: 
   ```bash
   git checkout -b feature/your-feature-name
   ```
4. **Make Your Changes**: Implement your feature or bug fix
5. **Test Your Changes**: Ensure all tests pass and add new tests if needed
6. **Commit Your Changes**: 
   ```bash
   git commit -m "Add: description of your changes"
   ```
7. **Push to Your Fork**: 
   ```bash
   git push origin feature/your-feature-name
   ```
8. **Submit a Pull Request**: Go to the original repository and create a pull request

### Contribution Guidelines

- Follow the existing code style and conventions
- Write clear, descriptive commit messages
- Add tests for new features
- Update documentation as needed
- Be respectful and constructive in discussions
- Test on multiple Android versions if possible

### Areas for Contribution

- **Feature Development**: Add new OTP detection patterns or utility features
- **UI/UX Improvements**: Enhance the Material 3 interface
- **Bug Fixes**: Help identify and fix issues
- **Documentation**: Improve README, code comments, or user guides
- **Testing**: Add unit tests or integration tests
- **Localization**: Add support for additional languages

## Community & Collaboration

SnagOTP is built on the principles of open-source collaboration. We believe that transparent development and community involvement create better, more trustworthy software. Join us in making OTP management easier for everyone!

## License

This project is open source and available for community contributions.
