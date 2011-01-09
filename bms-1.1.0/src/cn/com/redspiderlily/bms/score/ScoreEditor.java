package cn.com.redspiderlily.bms.score;

import org.eclipse.jface.action.ToolBarManager;
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
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import cn.com.redspiderlily.bms.archive.ArchiveEditorSorter;
import cn.com.redspiderlily.bms.model.Book;
import cn.com.redspiderlily.bms.system.EditorPartAdapter;
import cn.com.redspiderlily.bms.system.SmsContentProvider;
import cn.com.redspiderlily.bms.system.SmsFactory;
import cn.com.redspiderlily.bms.system.TableLabelProviderAdapter;
import cn.com.redspiderlily.bms.system.ViewerSortSelectionListener;


public class ScoreEditor extends EditorPartAdapter{
	private TableViewer tv;		//显示书籍的表格
	
	public void init(IEditorSite site,IEditorInput input)throws PartInitException{
		super.init(site, input);
		setPartName(input.getName());
	}
	
	public void createPartControl(Composite parent){
		ViewForm topComp = new ViewForm(parent, SWT.NONE);
		topComp.setLayout(new FillLayout());
		createTableViewer(topComp);
		tv.setContentProvider(new SmsContentProvider());
		tv.setLabelProvider(new TableViewerLabelProvider());
		
		ScoreEditorActionGroup actionGroup = new ScoreEditorActionGroup(tv);
		ToolBar toolBar = new ToolBar(topComp, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		actionGroup.fillActionToolBars(toolBarManager);
		
		topComp.setContent(tv.getControl());
		topComp.setTopLeft(toolBar);
	}
	
	private void createTableViewer(Composite parent){
		tv = new TableViewer(parent, SWT.MULTI|SWT.BORDER|SWT.FULL_SELECTION);
		Table table = tv.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayout(new TableLayout());
		
		createColumn(50,"书名",ArchiveEditorSorter.BOOKNAME_ASC,ArchiveEditorSorter.BOOKNAME_DESC);
		createColumn(40,"作者",ArchiveEditorSorter.BOOKAUTHOR_ASC,ArchiveEditorSorter.BOOKAUTHOR_DESC);
		createColumn(40,"主类",ArchiveEditorSorter.MAINCLASSIFICATION_ASC,ArchiveEditorSorter.MAINCLASSIFICATION_DESC);
		createColumn(40,"从类",null,null);
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
	
	public void setSearchedBookList(String searchKey, int searchType){
		switch(searchType){
		case 0 :{
				tv.setInput(SmsFactory.getDbOperate().getSyntheticalSearchedBookList(searchKey));
				break;
			}
		case 1 :{
				tv.setInput(SmsFactory.getDbOperate().getBookNameSearchedBookList(searchKey));
				break;
			}
		case 2:{
				tv.setInput(SmsFactory.getDbOperate().getAuthorSearchedBookList(searchKey));
				break;
			}
		}
	}
	
	private final static class TableViewerLabelProvider extends TableLabelProviderAdapter{
		public String getColumnText(Object element, int columnIndex){
			Book o = (Book)element;
			switch(columnIndex){
			case 0: return o.getBookName();
			case 1: return o.getBookAuthor();
			case 2: return o.getMainClassification();
			case 3: return o.getSubClassification();
			}
			return "";
		}
	}

}
