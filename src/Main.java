/**
 * Created by mesomaesonwune on 10/9/17.
 */
public class Main {
    public static void main(String[] args)
    {
        
        //Tester code
        //String smile = ".October.";
        //smile = encryptString(smile, 2, 3);
        //System.out.println(smile);
        //smile = decryptString(smile, 2);
        //System.out.println(smile);


    }

    //Get rid of all punctuation, spaces and capitalize everything
    public static String normalizeText(String string)
    {
        //array that has all punctuation we want to remove
        char[] remove = {'.', ',', ':', ';', '\'', '"', '!', '?', '(',')', ' '};
        //always checking to see if the given string is empty
        if (string == null)
        {
            return string;
        }

        String newString = "";

        //for the length of the string, go through and check and see if it
        //equals one of the things we need to remove
        for (int i = 0; i < string.length(); i++)
        {
            int count = 0;
            for (int j = 0; j < remove.length; j++)
            {
                //if it equals, add to the count
                if (string.charAt(i) == remove[j])
                {
                    count++;
                }
            }

            //if count is 0, then add the letter to the new string
            if (count == 0)
            {
                newString += string.charAt(i);
            }
        }

        //then make everything uppercase and return it
        newString = toUppercase(newString);
        return newString;
    }

    //go through the string obifying the letters
    public static String obify(String str)
    {
        //create an empty string and make sure it's not empty
        String newString = "";

        if (str == null)
        {
            return str;
        }

        //check to see if it's a vowel, if so then add OB in front
        //then the vowel next and return the string
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == 'A' || str.charAt(i) == 'E' ||
                str.charAt(i) == 'I' || str.charAt(i) == 'O' ||
                str.charAt(i) == 'U' || str.charAt(i) == 'Y')
            {
                newString += "OB";
            }

            newString += str.charAt(i);
        }

        return newString;
    }

    //reverse what we did in the obify method
    public static String unobify(String str)
    {
        //create empty string and make sure given string isn't
        //null
        String newString = "";

        if (str == null)
        {
            return str;
        }

        //scan through the string checking for OB and making sure
        //we haven't gone over the length of the string
        for (int i = 0; i < str.length(); i++)
        {
            //if yes, then skip OB and add the vowel
            if (str.charAt(i) == 'O' && str.charAt(i+1) == 'B' && i+2 < str.length())
            {
                i += 2;
                newString += str.charAt(i);
            }
            //if no, then just add the letter
            else
            {
                newString += str.charAt(i);
            }
        }
        //return the string
        return newString;
    }

    //convert to caesar
    public static String caesarify(String str, int key)
    {
        //have an original alphabet
        String newString = "";
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        if (str == null || key == 0)
        {
            return str;
        }

        //have an alphabet to shift through
        String shiftAlpha = shiftAlphabet(key);

        //scan through the original string and grab a letter
        for (int i = 0; i < str.length(); i++)
        {
            char letter = str.charAt(i);
            //scan through the original alphabet and see
            //where it matches up with
            for (int j = 0; j < alpha.length(); j++)
            {
                //now it looks at the caesar alphabet and grab
                //the letter that matches the index
                //save it to newString
                if (letter == alpha.charAt(j))
                {
                    newString += shiftAlpha.charAt(j);
                    break;
                }
            }
        }

        return newString;
    }

    //split up encrypted texts by equal groups
    public static String groupify(String str, int grouping)
    {
        //normalize the text just in case it hasn't happened already
        str = normalizeText(str);
        String newString = "";

        if (str == null)
        {
            return str;
        }

        //have a number storing the modulo
        int modulo = str.length()%grouping;

        for (int i = 0; i < str.length(); i++)
        {
            //if i isn't 0 and the remainder is 0
            //add a space and the letter
            if (i % grouping == 0 & i != 0)
            {
                newString += " " + str.charAt(i);
            }
            //otherwise just the letter
            else
            {
                newString += str.charAt(i);
            }
        }

        //this is just to make sure to add the x's if
        //there's a modulo value other than 0
        if (modulo != 0) {

            for (int i = modulo; i < grouping; i++)
            {
                newString += "x";
            }
        }

        return newString;
    }

    //call all encryption methods
    public static String encryptString(String str, int shift, int group)
    {
        String newString = "";
        newString = normalizeText(str);
        newString = obify(newString);
        newString = caesarify(newString,shift);
        newString = groupify(newString, group);

        return newString;
    }

    //reverse groupify method
    public static String ungroupify(String str)
    {
        //create an empty string and make sure
        //str isn't null
        String newString = "";

        if (str == null)
        {
            return str;
        }

        //check for lowercase x and don't put them
        //in new string
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == 'x')
            {
                break;
            }

            newString += str.charAt(i);
        }

        //run what's left to get rid of the spaces
        newString = normalizeText(newString);

        return newString;
    }

    public static String decryptString(String str, int shift)
    {
        String newString = "";
        newString = ungroupify(str);
        newString = caesarify(newString, -shift);
        newString = unobify(newString);
        return newString;
    }

    //my own version of toUppercase
    public static String toUppercase(String str)
    {
        String newString = "";
        char[] array = new char[str.length()];

        for (int i = 0; i < str.length(); i++)
        {
            array[i] = str.charAt(i);
        }

        for (int i = 0; i < array.length; i++)
        {
            switch (array[i])
            {
                case 'a':
                    array[i] = 'A';
                    break;
                case 'b':
                    array[i] = 'B';
                    break;
                case 'c':
                    array[i] = 'C';
                    break;
                case 'd':
                    array[i] = 'D';
                    break;
                case 'e':
                    array[i] = 'E';
                    break;
                case 'f':
                    array[i] = 'F';
                    break;
                case 'g':
                    array[i] = 'G';
                    break;
                case 'h':
                    array[i] = 'H';
                    break;
                case 'i':
                    array[i] = 'I';
                    break;
                case 'j':
                    array[i] = 'J';
                    break;
                case 'k':
                    array[i] = 'K';
                    break;
                case 'l':
                    array[i] = 'L';
                    break;
                case 'm':
                    array[i] = 'M';
                    break;
                case 'n':
                    array[i] = 'N';
                    break;
                case 'o':
                    array[i] = 'O';
                    break;
                case 'p':
                    array[i] = 'P';
                    break;
                case 'q':
                    array[i] = 'Q';
                    break;
                case 'r':
                    array[i] = 'R';
                    break;
                case 's':
                    array[i] = 'S';
                    break;
                case 't':
                    array[i] = 'T';
                    break;
                case 'u':
                    array[i] = 'U';
                    break;
                case 'v':
                    array[i] = 'V';
                    break;
                case 'w':
                    array[i] = 'W';
                    break;
                case 'x':
                    array[i] = 'X';
                    break;
                case 'y':
                    array[i] = 'Y';
                    break;
                case 'z':
                    array[i] = 'Z';
                    break;

            }
        }

        for (int i = 0; i < array.length; i++)
        {
            newString += array[i];
        }

        return newString;
    }

    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }
}
