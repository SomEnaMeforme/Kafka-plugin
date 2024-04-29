package core.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import core.producer.Producer;
import org.jetbrains.annotations.NotNull;

public class SendAction extends AnAction {
    public void actionPerformed(@NotNull AnActionEvent e) {
        String topic = Messages.showInputDialog("Enter topic name", "Topic", Messages.getQuestionIcon());
        String key = Messages.showInputDialog("Enter message key", "Key", Messages.getQuestionIcon());
        String value = Messages.showInputDialog("Enter message text", "Value", Messages.getQuestionIcon());
        new Producer().SendMessage(topic, key, value);
    }
}
