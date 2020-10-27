# NetworkState
[![](https://jitpack.io/v/genaku/NetworkState.svg)](https://jitpack.io/#genaku/NetworkState)

Online network state observable based on Kotlin coroutine flow

## Import

```Add it in your root build.gradle at the end of repositories:
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency:
```
dependencies {
	        implementation 'com.github.genaku:NetworkState:1.0.1'
	}
```

## Usage

```
val onlineState = getNetworkStateFlow(this)
lifecycleScope.launchWhenResumed {
    onlineState.collect { isOnline ->
        // do somethig
    }
}
```

To start observe network online state:
```
onlineState.start()
```

To stop observe network online state:
```
onlineState.stop()
```

## License
```
 Copyright 2020, Gena Kuchergin

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
