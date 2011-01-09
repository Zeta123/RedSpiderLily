package cn.com.redspiderlily.bms.classification;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import cn.com.redspiderlily.bms.archive.ArchiveEditorSorter;
import cn.com.redspiderlily.bms.db.DbOperate;
import cn.com.redspiderlily.bms.model.ClassificationNode;
import cn.com.redspiderlily.bms.system.EditorPartAdapter;
import cn.com.redspiderlily.bms.system.SmsContentProvider;
import cn.com.redspiderlily.bms.system.SmsFactory;
import cn.com.redspiderlily.bms.system.TableLabelProviderAdapter;
import cn.com.redspiderlily.bms.system.ViewerSortSelectionListener;

public class ClassificationEditor extends EditorPartAdapter{
	private TableViewer tv;
	private DbOperate db = SmsFactory.getDbOperate();
	
	public void createPartControl(Composite parent){
		ViewForm topComp = new ViewForm(parent, SWT.NONE);
		topComp.setLayout(new FillLayout());
		createTableViewer(topComp);
		tv.setContentProvider(new SmsContentProvider());
		tv.setLabelProvider(new TableViewerLabelProvider());
		
		topComp.setContent(tv.getControl());
		tv.setInput(db.getClassification());
	}
	
	
	private void createTableViewer(Composite parent){
		tv = new TableViewer(parent, SWT.MULTI|SWT.BORDER|SWT.FULL_SELECTION);
		Table table = tv.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayout(new TableLayout());
		
		createColumn(40,"主分类",ArchiveEditorSorter.MAINCLASSIFICATION_ASC,ArchiveEditorSorter.MAINCLASSIFICATION_DESC);
		createColumn(40,"二级分类",null,null);
	}
	
	private TableColumn createColumn(int weight,String name,ViewerSorter asc,ViewerSorter desc){
		Table table = tv.getTable();
		TableLayout layout = (TableLayout)table.getLayout();
		layout.addColumnData(new ColumnWeightData(weight));
		TableColumn col = new TableColumn(table, SWT.NONE);
		col.setText(name);
		if(asc != null && desc != null)
			col.addSelectionListener(new ViewerSortSelectionListener(tv,asc,desc));
		return col;
	}
	
	private static final class TableViewerLabelProvider extends TableLabelProviderAdapter{
		public String getColumnText(Object element, int col){
			ClassificationNode o = (ClassificationNode) element;
			switch(col){
			case 0:	return o.getMainClassification().toString();
			case 1:	return o.getSubClassification().toString();
			default:
				return "";
			}
		}
	}
}
