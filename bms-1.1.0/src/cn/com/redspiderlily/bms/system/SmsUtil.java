package cn.com.redspiderlily.bms.system;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import cn.com.redspiderlily.bms.db.DbOperate;

public class SmsUtil {
	private SmsUtil(){}
	
	public static Combo createMainClassificationCombo(Composite parent, int style){
		Combo comboMainClassification = new Combo(parent,style);
		DbOperate db = SmsFactory.getDbOperate();
		for(String mainClassification : db.getAllMainClassification()){
			comboMainClassification.add(mainClassification);
			comboMainClassification.setData(mainClassification, mainClassification);
		}
		return comboMainClassification;
	}
}
