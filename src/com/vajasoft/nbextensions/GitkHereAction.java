package com.vajasoft.nbextensions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import org.netbeans.api.project.Project;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.util.NbPreferences;

@ActionID(category = "Tools", id = "com.vajasoft.nbextensions.GitkHereAction")
@ActionRegistration(iconBase = "gitk-all-tp.png", displayName = "#CTL_GitkHereAction")
@ActionReferences({
    @ActionReference(path = "Menu/Tools"),
    @ActionReference(path = "Toolbars/Tools")
})
@Messages("CTL_GitkHereAction=Open Gitk")
public final class GitkHereAction implements ActionListener {
    private final Project project;
    private final String pathToGitk;

    public GitkHereAction(Project context) {
        project = context;
        String pref = NbPreferences.forModule(VajasoftPanel.class).get("gitkLocation", "");
        pathToGitk = pref.isEmpty() ? "gitk.exe" : "\"" + pref + "\"";
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        FileObject projectDirectory = project.getProjectDirectory();
        String path = projectDirectory.getPath();
        
        try {
            Runtime.getRuntime().exec(pathToGitk + " --all", null, new File(path));
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
