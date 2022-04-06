package ru.geekbrains.myapplication

import java.lang.Thread.sleep

class LoginPresenter : LoginContract.Presenter {

    private var view: LoginContract.View? = null

    override fun onAttach(view: LoginContract.View) {
        this.view = view
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            sleep(3_000)
            view?.hideProgress()
            if (login.equals(password)) {
                view?.setSuccess()
            } else {
                view?.setError("Incorrect password")
            }
        }.start()
    }

    override fun onCredentialsChanged() {
        TODO("Not yet implemented")
    }
}