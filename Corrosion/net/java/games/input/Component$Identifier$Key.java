package net.java.games.input;

public class Key extends Component.Identifier {
    public static final Key VOID = new Key("Void");
    public static final Key ESCAPE = new Key("Escape");
    public static final Key _1 = new Key("1");
    public static final Key _2 = new Key("2");
    public static final Key _3 = new Key("3");
    public static final Key _4 = new Key("4");
    public static final Key _5 = new Key("5");
    public static final Key _6 = new Key("6");
    public static final Key _7 = new Key("7");
    public static final Key _8 = new Key("8");
    public static final Key _9 = new Key("9");
    public static final Key _0 = new Key("0");
    public static final Key MINUS = new Key("-");
    public static final Key EQUALS = new Key("=");
    public static final Key BACK = new Key("Back");
    public static final Key TAB = new Key("Tab");
    public static final Key Q = new Key("Q");
    public static final Key W = new Key("W");
    public static final Key E = new Key("E");
    public static final Key R = new Key("R");
    public static final Key T = new Key("T");
    public static final Key Y = new Key("Y");
    public static final Key U = new Key("U");
    public static final Key I = new Key("I");
    public static final Key O = new Key("O");
    public static final Key P = new Key("P");
    public static final Key LBRACKET = new Key("[");
    public static final Key RBRACKET = new Key("]");
    public static final Key RETURN = new Key("Return");
    public static final Key LCONTROL = new Key("Left Control");
    public static final Key A = new Key("A");
    public static final Key S = new Key("S");
    public static final Key D = new Key("D");
    public static final Key F = new Key("F");
    public static final Key G = new Key("G");
    public static final Key H = new Key("H");
    public static final Key J = new Key("J");
    public static final Key K = new Key("K");
    public static final Key L = new Key("L");
    public static final Key SEMICOLON = new Key(";");
    public static final Key APOSTROPHE = new Key("'");
    public static final Key GRAVE = new Key("~");
    public static final Key LSHIFT = new Key("Left Shift");
    public static final Key BACKSLASH = new Key("\\");
    public static final Key Z = new Key("Z");
    public static final Key X = new Key("X");
    public static final Key C = new Key("C");
    public static final Key V = new Key("V");
    public static final Key B = new Key("B");
    public static final Key N = new Key("N");
    public static final Key M = new Key("M");
    public static final Key COMMA = new Key(",");
    public static final Key PERIOD = new Key(".");
    public static final Key SLASH = new Key("/");
    public static final Key RSHIFT = new Key("Right Shift");
    public static final Key MULTIPLY = new Key("Multiply");
    public static final Key LALT = new Key("Left Alt");
    public static final Key SPACE = new Key(" ");
    public static final Key CAPITAL = new Key("Caps Lock");
    public static final Key F1 = new Key("F1");
    public static final Key F2 = new Key("F2");
    public static final Key F3 = new Key("F3");
    public static final Key F4 = new Key("F4");
    public static final Key F5 = new Key("F5");
    public static final Key F6 = new Key("F6");
    public static final Key F7 = new Key("F7");
    public static final Key F8 = new Key("F8");
    public static final Key F9 = new Key("F9");
    public static final Key F10 = new Key("F10");
    public static final Key NUMLOCK = new Key("Num Lock");
    public static final Key SCROLL = new Key("Scroll Lock");
    public static final Key NUMPAD7 = new Key("Num 7");
    public static final Key NUMPAD8 = new Key("Num 8");
    public static final Key NUMPAD9 = new Key("Num 9");
    public static final Key SUBTRACT = new Key("Num -");
    public static final Key NUMPAD4 = new Key("Num 4");
    public static final Key NUMPAD5 = new Key("Num 5");
    public static final Key NUMPAD6 = new Key("Num 6");
    public static final Key ADD = new Key("Num +");
    public static final Key NUMPAD1 = new Key("Num 1");
    public static final Key NUMPAD2 = new Key("Num 2");
    public static final Key NUMPAD3 = new Key("Num 3");
    public static final Key NUMPAD0 = new Key("Num 0");
    public static final Key DECIMAL = new Key("Num .");
    public static final Key F11 = new Key("F11");
    public static final Key F12 = new Key("F12");
    public static final Key F13 = new Key("F13");
    public static final Key F14 = new Key("F14");
    public static final Key F15 = new Key("F15");
    public static final Key KANA = new Key("Kana");
    public static final Key CONVERT = new Key("Convert");
    public static final Key NOCONVERT = new Key("Noconvert");
    public static final Key YEN = new Key("Yen");
    public static final Key NUMPADEQUAL = new Key("Num =");
    public static final Key CIRCUMFLEX = new Key("Circumflex");
    public static final Key AT = new Key("At");
    public static final Key COLON = new Key("Colon");
    public static final Key UNDERLINE = new Key("Underline");
    public static final Key KANJI = new Key("Kanji");
    public static final Key STOP = new Key("Stop");
    public static final Key AX = new Key("Ax");
    public static final Key UNLABELED = new Key("Unlabeled");
    public static final Key NUMPADENTER = new Key("Num Enter");
    public static final Key RCONTROL = new Key("Right Control");
    public static final Key NUMPADCOMMA = new Key("Num ,");
    public static final Key DIVIDE = new Key("Num /");
    public static final Key SYSRQ = new Key("SysRq");
    public static final Key RALT = new Key("Right Alt");
    public static final Key PAUSE = new Key("Pause");
    public static final Key HOME = new Key("Home");
    public static final Key UP = new Key("Up");
    public static final Key PAGEUP = new Key("Pg Up");
    public static final Key LEFT = new Key("Left");
    public static final Key RIGHT = new Key("Right");
    public static final Key END = new Key("End");
    public static final Key DOWN = new Key("Down");
    public static final Key PAGEDOWN = new Key("Pg Down");
    public static final Key INSERT = new Key("Insert");
    public static final Key DELETE = new Key("Delete");
    public static final Key LWIN = new Key("Left Windows");
    public static final Key RWIN = new Key("Right Windows");
    public static final Key APPS = new Key("Apps");
    public static final Key POWER = new Key("Power");
    public static final Key SLEEP = new Key("Sleep");
    public static final Key UNKNOWN = new Key("Unknown");

    protected Key(String name) {
        super(name);
    }
}
