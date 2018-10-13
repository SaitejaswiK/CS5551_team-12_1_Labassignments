package tutorial.cs5551.com.classifyText;

    import android.content.Intent;
import android.os.Bundle;
    import android.support.annotation.NonNull;
    import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import tutorial.cs5551.com.translateapp.R;

    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth=FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void checkCredentials(View v) {
        EditText usernameCtrl = (EditText) findViewById(R.id.txt_uname);
        EditText passwordCtrl = (EditText) findViewById(R.id.txt_Pwd);
        TextView errorText = (TextView) findViewById(R.id.lbl_Error);
        String userName = usernameCtrl.getText().toString();
        String password = passwordCtrl.getText().toString();

        auth.signInWithEmailAndPassword(userName, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isComplete()) {

                        } else {
                            Intent redirect = new Intent(LoginActivity.this, ClassifyActivity.class);
                            startActivity(redirect);
                        }

                    }
                });
    }

    public void register(View v)
    {
        EditText usernameCtrl = (EditText)findViewById(R.id.txt_uname);
        EditText passwordCtrl = (EditText) findViewById(R.id.txt_Pwd);
        TextView errorText = (TextView)findViewById(R.id.lbl_Error);
        String userName = usernameCtrl.getText().toString();
        String password = passwordCtrl.getText().toString();

        auth.createUserWithEmailAndPassword(userName,password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isComplete()) {

                } else {
                    Intent redirect = new Intent(LoginActivity.this, ClassifyActivity.class);
                    startActivity(redirect);
                }
            }
});

        //}

    }
}
