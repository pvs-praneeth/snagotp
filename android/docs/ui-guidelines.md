# UI Guidelines

## SNAGOTP Android App - Material 3 UI Standards

Status: Draft - To Be Developed
Last Updated: October 31, 2025

---

## Design Principles

- Follow Material Design 3 (Material You)
- Support dynamic color theming on Android 12+
- Ensure accessibility (contrast, font scaling, TalkBack)
- Use responsive/adaptive layouts (phones, tablets, foldables)
- Provide dark mode and respect system theme

## Components and Patterns

### App Structure
- Single-Activity architecture with Navigation Component
- TopAppBar/NavigationBar based on screen
- Use Navigation Drawer for multi-section apps (if needed)

### Color and Theming
- Dynamic color (Material You) on supported devices
- Fallback color scheme for API < 31
- Light and dark color schemes

### Typography
- Use Material 3 typography scale
- Respect user font size settings
- Avoid text truncation; prefer wrapping or marquee for critical labels

### Elevation and Shapes
- Use default M3 shapes and elevation levels
- Avoid excessive elevation to reduce visual noise

### Lists and Cards
- Use RecyclerView with ListAdapter + DiffUtil
- Employ M3 Cards for grouped content
- Use placeholders/shimmers for loading states

### Buttons and Interactions
- Use M3 buttons (Filled, Tonal, Outlined, Text) appropriately
- Provide clear affordances and feedback (ripples, state changes)
- Disable actions during network calls; show progress indicators

### Dialogs and Sheets
- Use ModalBottomSheet for contextual actions
- Use AlertDialog for confirmations and critical alerts
- Ensure dialogs are cancellable where appropriate

### Forms and Inputs
- Use TextInputLayout with end icons for visibility toggles
- Validate input in real time and show helpful messages
- Support autofill where possible

## Layout Guidelines

- ConstraintLayout for complex layouts; LinearLayout for simple stacks
- Use dimension resources for paddings/margins
- Support multiple screen sizes with qualifiers (sw600dp, etc.)
- Maintain minimum touch target size (48dp)

## States and Feedback

- Show loading, empty, error, and content states distinctly
- Use Snackbars for transient messages; avoid toasts for critical info
- Use pull-to-refresh where appropriate

## Icons and Imagery

- Use Material Symbols
- Provide vector assets (SVG -> VectorDrawable)
- Respect RTL layouts for bidirectional languages

## Accessibility

- Content descriptions for non-text UI
- Sufficient contrast ratio (WCAG AA)
- Keyboard navigation and focus order
- Test with TalkBack and Switch Access

## Internationalization

- All strings in strings.xml
- Avoid concatenating localized strings
- Support pluralization and formatting

## Performance Considerations

- Minimize overdraw; use background colors wisely
- Reuse view holders and avoid nested weights
- Preload critical data for perceived performance

## Sample XML Snippets

```xml
<com.google.android.material.appbar.MaterialToolbar
    android:id="@+id/toolbar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:title="@string/app_name"
    app:navigationIcon="@drawable/ic_back" />

<com.google.android.material.textfield.TextInputLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:hintEnabled="true">

    <com.google.android.material.textfield.TextInputEditText
        android:id="@+id/et_email"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:inputType="textEmailAddress" />
</com.google.android.material.textfield.TextInputLayout>
```

## Resources

- Material Design 3: https://m3.material.io/
- Android UI Guidelines: https://developer.android.com/design
- Accessibility: https://developer.android.com/guide/topics/ui/accessibility

---

Contributions and updates to this guide are welcome.
