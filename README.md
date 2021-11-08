# funclang
FuncLang: A Language with Functions

These are the key functionalities added:

1. Bashemulator that takes care of some basic bash-like functionality by maintaining a history, and formatting outputs
2. Global key listener that listens for escape key to disconnect listener or bracket open and close key for retrieving bash history
3. Changes to the Reader allows functionality to read multiple lines and can even ignore lines starting with #
4. Typing !read FILENAME can allow you to read from a well formatted file with programs written over multiple lines with # for comments, these will be saved to your bash history. Currently the FILE needs to be saved inside the folder 'playground' to work. By default !read without filename will read from playground.scm

Regarding Key Bindings:
  
  The RobotKeyboard class contains all the key bindings and they might not fit into every keyboard, so you will need to edit the key bindings according to your keyboard\
  \
  For instance, if you need to press 'Shift' + '=' to get a '+', then in the 'type' method you might need to make an entry that looks like this:\
  \
  case '+': doType(VK_SHIFT, VK_EQUALS); break;\
  \
  This is already implemented, however, other key bindings may differ.
