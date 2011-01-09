package cn.com.redspiderlily.bms;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private IWorkbenchAction quitAction;
	private IWorkbenchAction aboutAction;
	private IWorkbenchAction newWindowAction;
	private IAction scorePersAction;
	private IWorkbenchAction preferencesAction;
	
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	quitAction = ActionFactory.QUIT.create(window);
    	aboutAction = ActionFactory.ABOUT.create(window);
    	newWindowAction = ActionFactory.OPEN_NEW_WINDOW.create(window);
    	scorePersAction = new ScorePerspectiveAction();
    	preferencesAction = ActionFactory.PREFERENCES.create(window);
    	register(quitAction);
    	register(aboutAction);
    	register(newWindowAction);
    	register(scorePersAction);
    	register(preferencesAction);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	IMenuManager fileMenu = new MenuManager("ÎÄ¼þ(&F)",IWorkbenchActionConstants.M_FILE);
    	IMenuManager persMenu = new MenuManager("Í¸ÊÓÍ¼(&P)");
    	IMenuManager helpMenu = new MenuManager("°ïÖú(&H)",IWorkbenchActionConstants.M_HELP);
    	menuBar.add(fileMenu);
    	menuBar.add(persMenu);
    	menuBar.add(helpMenu);
    	
    	fileMenu.add(newWindowAction);
    	fileMenu.add(preferencesAction);
    	fileMenu.add(new Separator());
    	fileMenu.add(quitAction);
    	persMenu.add(scorePersAction);
    	helpMenu.add(aboutAction);
    }
    
    protected void fillCoolBar(ICoolBarManager coolBar){
    	IToolBarManager toolbar = new ToolBarManager(SWT.FLAT|SWT.RIGHT);
    	coolBar.add(new ToolBarContributionItem(toolbar,"main"));
    	toolbar.add(scorePersAction);
    	toolbar.add(quitAction);
    }
    
    private static class ScorePerspectiveAction extends Action{
    	public ScorePerspectiveAction(){
    		setId("ACTIONS_SCORE_PERSPECTIVE");
    		setText("score");
    		setImageDescriptor(Activator.getImageDescriptor("icons/alt_window_16.gif"));
    	}
    	public void run(){
    		Activator.showPerspective("cn.com.redspiderlily.bms.perspective");
    	}
    }
}
