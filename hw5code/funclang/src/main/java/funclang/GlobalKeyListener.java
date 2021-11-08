package funclang;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListener implements NativeKeyListener {
    private bashemulator bash;
    private RobotKeyboard keyboard;

    public GlobalKeyListener(bashemulator bash, RobotKeyboard keyboard){
        super();
        this.bash = bash;
        this.keyboard = keyboard;

    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        //System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {

            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {

        if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals("Open Bracket")){

            for (int i = 0; i < 200; i++){
                //System.out.print("\b");
                keyboard.type('\b');
            }
            String lastInst = bash.getNewLast();
            //System.out.println(lastInst);
            keyboard.type(lastInst);

        }

        if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals("Close Bracket")){

            for (int i = 0; i < 200; i++){
                //System.out.print("\b");
                keyboard.type('\b');
            }
            String lastInst = bash.getOldLast();
            //System.out.println(lastInst);
            keyboard.type(lastInst);

        }

        //System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        //System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }

}
