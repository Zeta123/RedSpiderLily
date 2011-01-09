package cn.com.redspiderlily.bms.score;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;


public class SearchView extends ViewPart implements ModifyListener{
	private Text searchText;
	private Button searchRadioSynthetical,searchRadioBookName,searchRadioAuthor;
	private Composite parent;
	private Group group;
	
	public void createPartControl(Composite parent){
		this.parent = parent;
		createGroup();
	}
	
	private void createGroup(){
		group = new Group(parent, SWT.NONE);
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		group.setLayout(new FillLayout(SWT.VERTICAL));
		group.setText("根据书名或作者名查询书籍");
		
		searchText = new Text(group,SWT.BORDER);
		searchText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		searchText.addModifyListener(this);
		
		searchRadioSynthetical = new Button(group,SWT.RADIO);
		searchText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		searchRadioSynthetical.setText("全部");
		searchRadioSynthetical.setSelection(true);	
		
		searchRadioBookName = new Button(group,SWT.RADIO);
		searchText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		searchRadioBookName.setText("书名");
		
		searchRadioAuthor = new Button(group,SWT.RADIO);
		searchText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		searchRadioAuthor.setText("作者");
		
		Button searchButton = new Button(group,SWT.READ_ONLY);
		searchButton.setText("搜索");
		searchButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				String searchKey = searchText.getText();
				
				try{
					ScoreEditorInput ei = new ScoreEditorInput();
					
					ei.setName("搜索结果： " + searchKey);
					String editorId = ScoreEditor.class.getName();
					IWorkbenchPage workbenchPage = getViewSite().getPage();
					ScoreEditor editor = (ScoreEditor)workbenchPage.openEditor(ei,editorId);
					if(searchRadioSynthetical.getSelection())
						editor.setSearchedBookList(searchKey,0);
					if(searchRadioBookName.getSelection())
						editor.setSearchedBookList(searchKey,1);
					if(searchRadioAuthor.getSelection())
						editor.setSearchedBookList(searchKey,2);
				}catch(PartInitException e2){e2.printStackTrace();} 
			}
		});
	}
	
	public void setFocus(){}
	
	public void modifyText(ModifyEvent e){
		if(searchText.getText().trim().equals("")){
			MessageDialog.openError(null, "错误提示", "必须填写搜索关键字");
			return;
		}
	}
}
