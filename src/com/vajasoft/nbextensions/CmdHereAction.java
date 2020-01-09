package com.vajasoft.nbextensions;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Tools", id = "com.vajasoft.nbextensions.CmdHereAction")
@ActionRegistration(iconBase = "cmd-here-tp.png", displayName = "#CTL_CmdHereAction")
@ActionReferences({
    @ActionReference(path = "Menu/Tools"),
    @ActionReference(path = "Toolbars/Tools"),
    @ActionReference(path = "Projects/package/Actions"),
    @ActionReference(path = "Loaders/image/png-gif-jpeg-bmp/Actions"),
    @ActionReference(path = "Loaders/text/x-java/Actions")
})
@Messages("CTL_CmdHereAction=Open Command Prompt")
public final class CmdHereAction implements ActionListener {
    private final DataObject context;

    public CmdHereAction(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        FileObject file = context.getPrimaryFile();
        if (!file.isFolder()) {
            file = file.getParent();
        }
        try {
            Runtime.getRuntime().exec("cmd.exe /C start cmd.exe", null, new File(file.getPath()));
//            Runtime.getRuntime().exec("cmd.exe /K cd " + file.getPath());
//            Process p = new ProcessBuilder("cmd.exe").directory(new File(file.getPath())).start();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
