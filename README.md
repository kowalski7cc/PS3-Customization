# PS3 Customizations #
![PS3 Customization logo](https://bitbucket.org/repo/LzBMnK/images/3272281403-PS3_Customization-01.png)
![Gnu GPL v3 logo](https://www.gnu.org/graphics/gplv3-127x51.png)
This is a tool for making modded ps3 customizations easier

## Features ##
* Upload single customization
* Upload a set of customizations
* Remove singrar from your CFW
* Set a PSN motd (coming soon)
* Reboot your console (uses webman API)
* Set everything back to stock-ish (won't roll back to OWF)

## Usage ##
Launch the application once, it will create a folder in your home ("c:\user\yourname\PS3 Customization" on windows)
Put a folder with the name of the customization inside the correct customization type with the original file name.
Example:

```
#!text

-PS3 Customization
--Boot sounds
---Stock
----coldboot_multi.ac3
----coldboot_stereo.ac3
---Classic
----coldboot_multi.ac3
----coldboot_stereo.ac3
--Waves
---Stock
----lines.qrc
```

## Common issues ##
You must first disable WebMan's integrated FTP and use Rebug toolbox or MultiMan integrated FTP or it won't work

## Screenshots ##
Coming soon

## License ##
```
#!text
    Copyright (C) 2016  Kowalski7cc

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
	```