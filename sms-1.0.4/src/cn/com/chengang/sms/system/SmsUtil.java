package cn.com.chengang.sms.system;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import cn.com.chengang.sms.db.DbOperate;

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
	
/*	public static Combo createSubClassificationCombo(Composite parent, int style, String mainClassification){
		Combo comboSubClassification = new Combo(parent,style);
		DbOperate db = SmsFactory.getDbOperate();
		for(String subClassification : db.getAllSubClassification(mainClassification)){
			comboSubClassification.add(subClassification);
			comboSubClassification.setData(subClassification, subClassification);
		}
		return comboSubClassification;
	}*/
}
