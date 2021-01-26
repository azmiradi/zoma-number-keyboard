# Zoma Number Keyboard  [![](https://jitpack.io/v/azmiradi/zoma-number-keyboard.svg)](https://jitpack.io/#azmiradi/zoma-number-keyboard)

Android library that provides a number keyboard view.
## Screenshot Picture -

<p align="center">
  <img src="https://github.com/azmiradi/zoma-number-keyboard/blob/master/screens/screen_mobile1.jpg" width="350" title="Screen mobile">
  <img src="https://github.com/azmiradi/zoma-number-keyboard/blob/master/screens/screen_mobile2.jpg" width="350" title="Screen mobile">
  <img src="https://github.com/azmiradi/zoma-number-keyboard/blob/master/screens/screen_tablet1.png" width="350" title="Screen Tablet">
   <img src="https://github.com/azmiradi/zoma-number-keyboard/blob/master/screens/screen_tablet2.png"  width="350" title="Screen Tablet">
</p>


## Usage

#### Step 1

Add the JitPack repository to your `build.gradle ` file:

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

#### Step 2

Add the dependency:

```gradle
dependencies {
    implementation 'com.github.azmiradi:zoma-number-keyboard:2.0.8'
}
```

 
#### Step 3

Use `NumberKeyboard` view in your layout:

```xml
<azmi.radi.com.keyboard.ZomaNumberKeyBoard
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    ...
  app:numberkeyboard_keyPadding="@dimen/_1sdp"
                          ... />
```

#### Attributes

 
 
- numberkeyboard_keyTextSize="[dimension]"` 
- numberkeyboard_keyPadding="[dimension]"` 
- numberkeyboard_numberKeyBackground="[reference]"` //color 
- numberkeyboard_numberKeyTextColor="[reference]"` //color 
- numberkeyboard_numberKeyBackgroundTint="[reference]"` //color 

#### Methods

 
- `setKeyWidth()`: sets key width in px.
- `setKeyHeight()`: sets key height in px.
- `setKeyPadding()`: sets key padding in px.
- `setNumberKeyBackground()`: sets number keys background.
- `setNumberKeyTextColor()`: sets number keys text color.
- `setNumberKeyTypeface()`: sets number keys text typeface.
 - `setNumberKeyTextSize()`: sets number keys text size.
- `setNumberKeyBackgroundTint()`: sets number keys tint.

#### Callback

To listen to keyboard events, you have to use `ZomaNumberKeyBoardListener`:

- `onNumberClicked()`: invoked when a number key is clicked.
- `onClearClicked()`: invoked when the Clear button is clicked.
- `onCommaClicked()`: invoked when the Comma button is clicked.
- `onLongClearClicked()`: invoked when the LongClear button is clicked.

```java
 EditText editText=findViewById(R.id.edit);
ZomaNumberKeyBoard zomaNumberKeyBoard =findViewById(R.id.ZomaKeyBoard);
         zomaNumberKeyBoard.setOnListener(new ZomaNumberKeyBoardListener() {
            @Override
            public void onNumberClicked(int value) {
                editText.append(String.valueOf(value));
             }

            @Override
            public void onCommaClicked() {
                editText.append(",");

            }

            @Override
            public void onClearClicked() {
                if (!TextUtils.isEmpty( editText.getText().toString())) {
                    String str = editText.getText().toString();
                    str = str.substring(0, str.length() - 1);
                    editText.setText(str);
                }

            }
            @Override
            public void onLongClearClicked() {
                editText.setText("");
            }
        });
```

Take a look at the [sample app](https://github.com/azmiradi/zoma-number-keyboard/tree/master/app) to see the library working.

## Contributing

If you find any issues or you have any questions, ideas... feel free to [open an issue](https://github.com/azmiradi/zoma-number-keyboard/issues/new).
Pull request are very appreciated.

## License

Copyright (c) 2020 Azmi Radi Azmi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
