package ru.geekbrains.myapplication

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import ru.geekbrains.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var binding: ActivityMainBinding
    private var presenter: LoginContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = LoginPresenter()
        presenter?.onAttach(this)

        binding.loginButton.setOnClickListener {
            presenter?.onLogin(
                binding.loginEditTest.text.toString(),
                binding.passwordEditTest.text.toString()
            )
        }
    }

    override fun setSuccess() {
        binding.loginButton.isVisible = false
        binding.loginEditTest.isVisible = false
        binding.passwordEditTest.isVisible = false
        binding.root.setBackgroundColor(Color.GREEN)
    }

    override fun setError(error: String) {
        Toast.makeText(this, "ERROR: $error", Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        binding.loginButton.isEnabled = false
    }

    override fun hideProgress() {
        binding.loginButton.isEnabled = true
    }
}