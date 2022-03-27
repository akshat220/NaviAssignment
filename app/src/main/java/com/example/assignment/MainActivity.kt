package com.example.assignment

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelProvider.AndroidViewModelFactory(application)
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initListeners()
        initUi()
        observeData()
    }

    private fun observeData() {
        viewModel.submitDetailLiveData.observe(this) {
            when(it) {
                is ViewState.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
                is ViewState.SubmitSuccessful -> {
                    binding.progress.visibility = View.INVISIBLE
                    Toast.makeText(this, it.submitText, Toast.LENGTH_SHORT).show()
                    finish()
                }
                is ViewState.ActivateButton -> {
                    binding.progress.visibility = View.INVISIBLE
                    activateButton(it.boolean)
                }
            }
        }
    }

    private fun initUi() {
        with(binding) {
            tvHeading.text = getString(R.string.heading)
            tvConsent.text = setSpan()
            tvConsent.movementMethod = LinkMovementMethod.getInstance()
            activateButton(false)

            binding.etDay.setOnClickListener {
                openDatePicker()
            }
            binding.etMonth.setOnClickListener {
                openDatePicker()
            }
            binding.etYear.setOnClickListener {
                openDatePicker()
            }
            buttonSecondary.setOnClickListener {
                finish()
            }
            buttonPrimary.setOnClickListener {
                viewModel.submitDetails()
            }
        }
    }

    private fun openDatePicker() {
        DatePickerFragment(
            date = { year, month, day ->
                binding.etDay.text = day.toString()
                binding.etMonth.text = month.toString()
                binding.etYear.text = year.toString()
                viewModel.validateData(binding.etPan.text.toString(), day.toString(), month.toString(), year.toString())
            }
        ).show(supportFragmentManager, this.javaClass.simpleName)
    }

    private fun initListeners() {
        binding.etPan.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                viewModel.validateData(p0.toString(), binding.etDay.text.toString(), binding.etMonth.text.toString(), binding.etYear.text.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun activateButton(boolean: Boolean) {
        binding.buttonPrimary.isEnabled = boolean
        if (boolean) {
            binding.buttonPrimary.alpha = 1f
        } else {
            binding.buttonPrimary.alpha = .7f
        }
    }

    private fun setSpan(): SpannableString {
        val s = SpannableString(getString(R.string.consent))
        val clickableSpan = object: ClickableSpan() {
            override fun onClick(p0: View) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://fi.money/")))
            }
        }
        s.setSpan(clickableSpan, getString(R.string.consent).length - getString(R.string.consent_highlight).length, getString(R.string.consent).length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return s
    }
}