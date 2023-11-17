package br.ufrn.imd.imd_market.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.ufrn.imd.imd_market.R;
import br.ufrn.imd.imd_market.managers.AuthManager;
import br.ufrn.imd.imd_market.managers.UserManager;
import br.ufrn.imd.imd_market.models.User;
import br.ufrn.imd.imd_market.repositories.user.IUserRepository;
import br.ufrn.imd.imd_market.repositories.user.UserRepositorySQLite;
import br.ufrn.imd.imd_market.utils.AppContext;

public class MainActivity extends AppCompatActivity {
    private AuthManager authManager;
    private EditText loginEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppContext.getInstance().setCurrentContext(this);
        final IUserRepository userRepository = UserRepositorySQLite.getInstance();
        final UserManager userManager = UserManager.getInstance(userRepository);
        this.authManager = AuthManager.getInstance(userManager);

        this.loginEditText = (EditText) findViewById(R.id.loginEditText);
        this.passwordEditText = (EditText) findViewById(R.id.passwordEditText);
    }

    public void sign(View v) {
        try{
            final String login = this.loginEditText.getText().toString();
            final String password = this.passwordEditText.getText().toString();

            final User user = this.authManager.signIn(login, password);

            if(user != null) navigateToHome();
        }catch (Exception e) {
            this.showMessage("Autenticação falhou", e.getMessage());
        }
    }

    public void navigateToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.show();
    }
}