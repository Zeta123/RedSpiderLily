import static org.junit.Assert.*;
import org.junit.Test;
import cn.com.redspiderlily.bms.score.ScoreEditorInput;

public class TESTScoreEditorInput {
	@Test public void TESTsetName() {
		ScoreEditorInput SEI=new ScoreEditorInput();
		SEI.setName("���Ʋ���");
		assertEquals("���Ʋ���",SEI.getName());
	}
}
