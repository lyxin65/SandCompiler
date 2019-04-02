package Mxstar.Worker.FrontEnd;

import Mxstar.AST.TokenLocation;
import Mxstar.Worker.ErrorRecorder;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;


public class SyntaxErrorListener extends BaseErrorListener {
    public ErrorRecorder recorder;

    public SyntaxErrorListener(ErrorRecorder recorder) {
        this.recorder = recorder;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        recorder.addRecord(new TokenLocation(line, charPositionInLine), msg);
    }

    public boolean isError() {
        return recorder.errorOccured();
    }
}
