# no-wo-read

An CLI app for word remembering
You create `base.json` with content:
```json
{
  "cat": {
    "translation": "кот",
    "attempts": 6
  },
  "eagle": {
    "translation": "орел",
    "attempts": 5
  }
}
```
And then program asks you the less successfully answered word.

## Usage
Arch: 
```
yay -S lein
lein run
```

Type exit to exit and save attemts to json

## Examples
```
Translate the word: house 5
what
Invalid answer. Please try again.
Translate the word: house 5
дом
Valid answer!
Translate the word: eagle 5
орел
Valid answer!
Translate the word: cat 6
```
## License

Copyright © 2023 gavr132456789

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
