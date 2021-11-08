# funclang
FuncLang: A Language with Functions

These are the key functionalities added:

1. Bashemulator that takes care of some basic bash-like functionality by maintaining a history, and formatting outputs
2. Global key listener that listens for escape key to disconnect listener or bracket open and close key for retrieving bash history
3. Changes to the Reader allows functionality to read multiple lines and can even ignore lines starting with #
4. Typing !read <filename> can allow you to read from a well formatted file with programs written over multiple lines with # for comments, these will be saved to your bash history

