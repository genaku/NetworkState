# NetworkState
[![](https://jitpack.io/v/genaku/NetworkState.svg)](https://jitpack.io/#genaku/NetworkState)

Online network state observable based on Kotlin coroutine flow

# Import

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

# Usage

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
