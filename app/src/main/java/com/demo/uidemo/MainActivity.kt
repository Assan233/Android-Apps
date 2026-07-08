package com.demo.uidemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.demo.uidemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "UIDemo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate: Activity created")

        setupButton()
        setupCheckBox()
        setupRadioGroup()
        setupSwitch()
        setupSeekBar()
        setupSpinner()
    }

    private fun setupButton() {
        binding.btnPrimary.setOnClickListener {
            val input = binding.etInput.text.toString()
            val msg = if (input.isNotEmpty()) "你输入了: $input" else "Hello, Android!"
            Log.d(TAG, "Button clicked, message: $msg")
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        binding.btnSecondary.setOnClickListener {
            binding.etInput.text?.clear()
            binding.tvStatus.text = "状态: 已重置"
            Log.d(TAG, "Secondary button clicked, input cleared")
        }
    }

    private fun setupCheckBox() {
        binding.cbOption1.setOnCheckedChangeListener { _, isChecked ->
            Log.d(TAG, "CheckBox Option1: $isChecked")
            updateStatus()
        }
        binding.cbOption2.setOnCheckedChangeListener { _, isChecked ->
            Log.d(TAG, "CheckBox Option2: $isChecked")
            updateStatus()
        }
    }

    private fun setupRadioGroup() {
        binding.rgOptions.setOnCheckedChangeListener { _, checkedId ->
            val selected = when (checkedId) {
                R.id.rbOption1 -> "选项 A"
                R.id.rbOption2 -> "选项 B"
                R.id.rbOption3 -> "选项 C"
                else -> "未知"
            }
            Log.d(TAG, "RadioGroup selected: $selected")
            binding.tvStatus.text = "状态: 已选择 $selected"
        }
    }

    private fun setupSwitch() {
        binding.switchToggle.setOnCheckedChangeListener { _, isChecked ->
            Log.d(TAG, "Switch toggled: $isChecked")
            binding.tvStatus.text = if (isChecked) "状态: 开关已打开" else "状态: 开关已关闭"
        }
    }

    private fun setupSeekBar() {
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tvSeekBarValue.text = "进度: $progress%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Log.d(TAG, "SeekBar touch started")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d(TAG, "SeekBar value: ${seekBar?.progress}")
            }
        })
    }

    private fun setupSpinner() {
        val items = listOf("请选择...", "选项一", "选项二", "选项三", "选项四")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0) {
                    Log.d(TAG, "Spinner selected: ${items[position]}")
                    binding.tvStatus.text = "状态: 下拉选择了 ${items[position]}"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun updateStatus() {
        val opt1 = if (binding.cbOption1.isChecked) "选项1" else ""
        val opt2 = if (binding.cbOption2.isChecked) "选项2" else ""
        val selected = listOf(opt1, opt2).filter { it.isNotEmpty() }.joinToString(", ")
        binding.tvStatus.text = if (selected.isNotEmpty()) "状态: 已勾选 $selected" else "状态: 无勾选"
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}
