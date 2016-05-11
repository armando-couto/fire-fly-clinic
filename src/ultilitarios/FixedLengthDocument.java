package ultilitarios;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class FixedLengthDocument extends PlainDocument {
    private int maxLength;

    public FixedLengthDocument(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null){
            
            return;
        }
        if (maxLength <= 0) { // aceitara qualquer no. de caracteres
            
            super.insertString(offset, str, attr);
            return;
        }

        int length = getLength() + str.length();

        if (length <= maxLength) { // se o comprimento final for menor...
            
            super.insertString(offset, str, attr); // ...aceita 'str' completa
        } else {
            
            super.insertString(offset, str.substring(0, maxLength - getLength()), attr); // ...aceita uma parte de 'str'
        }
    }
}