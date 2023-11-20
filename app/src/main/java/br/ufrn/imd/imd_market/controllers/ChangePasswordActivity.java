package br.ufrn.imd.imd_market.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.ufrn.imd.imd_market.R;
import br.ufrn.imd.imd_market.managers.UserManager;
import br.ufrn.imd.imd_market.models.User;
import br.ufrn.imd.imd_market.repositories.user.IUserRepository;
import br.ufrn.imd.imd_market.repositories.user.UserRepositorySQLite;
import br.ufrn.imd.imd_market.utils.AppContext;
import br.ufrn.imd.imd_market.utils.MessageDisplay;

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText loginEditText;
    private EditText passwordEditText;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);


        AppContext.getInstance().setCurrentContext(this);
        final IUserRepository userRepository = UserRepositorySQLite.getInstance();
        this.userManager = UserManager.getInstance(userRepository);

        this.loginEditText = (EditText) findViewById(R.id.loginEditText);
        this.passwordEditText = (EditText) findViewById(R.id.passwordEditText);
    }

    public void updatePassword(View v) {
        try{
            final String login = this.loginEditText.getText().toString();
            final String password = this.passwordEditText.getText().toString();

            if(login.isEmpty()) throw new Exception("O login é obrigatório");
            if(password.isEmpty()) throw new Exception("A senha é obrigatória");

            this.userManager.changePasswordByLogin(login, password);
            MessageDisplay.showMessage("Senha alterada", "A senha do login " + login + " foi alterada com sucesso", this);
            this.clearFields();
        }catch (Exception e) {
            MessageDisplay.showMessage("Erro ao alterar senha", e.getMessage(), this);
        }
    }

    public void clearFieldsOnClick(View v) {
        this.clearFields();
    }

    private void clearFields() {
        this.loginEditText.setText("");
        this.passwordEditText.setText("");
    }
}