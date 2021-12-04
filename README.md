# pythonify_2

This repository is an IntelliJ IDEA plugin that can detect antipatterns in Python code and offers quick fixes for many of them.

<!--
![Build](https://github.com/ken437/pythonify_2/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)

## Template ToDo list
- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [ ] Get known with the [template documentation][template].
- [ ] Verify the [pluginGroup](/gradle.properties), [plugin ID](/src/main/resources/META-INF/plugin.xml) and [sources package](/src/main/kotlin).
- [ ] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [ ] Set the Plugin ID in the above README badges.
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.
-->
<!-- Plugin description -->
## Antipatterns
### E1: Access from end of array without negative index
Antipattern example:
```
a = [1, 2, 3]
b = a[len(a) - 1]
```
Fix:
```
a = [1, 2, 3]
b = a[-1]
```
### E2: Star import
Antipattern example:
```
from math import *
```
### E3: Use of mutable global variable
Antipattern example:
```
the_glob = 1

def func1():
    global the_glob
    the_glob = 2
```

### E4: Creating empty list using constructor rather than literal
Antipattern example:
```
a = list()
```
Fix:
```
a = []
```

### E5: Creating empty dict using constructor rather than literal
Antipattern example:
```
a = dict()
```
Fix:
```
a = {}
```
<!-- Plugin description end -->

<!--
## Installation

- Using IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "pythonify_2"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/ken437/pythonify_2/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

-->
---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
