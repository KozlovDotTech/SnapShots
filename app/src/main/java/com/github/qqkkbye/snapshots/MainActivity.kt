package com.github.qqkkbye.snapshots

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.github.qqkkbye.snapshots.filters.BitmapFilter
import com.github.qqkkbye.snapshots.filters.color.DownLightFilter
import com.github.qqkkbye.snapshots.filters.color.DownSaturationFilter
import com.github.qqkkbye.snapshots.filters.color.UpLightFilter
import com.github.qqkkbye.snapshots.filters.color.UpSaturationFilter
import com.github.qqkkbye.snapshots.filters.convolution.BlurFilter
import com.github.qqkkbye.snapshots.filters.convolution.ClarityFilter
import com.github.qqkkbye.snapshots.filters.convolution.ReliefFilter
import com.github.qqkkbye.snapshots.filters.median.BuildUpFilter
import com.github.qqkkbye.snapshots.filters.median.ErosionFilter
import com.github.qqkkbye.snapshots.filters.median.MedianFilter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileNotFoundException
import java.util.*
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val width: Int = windowManager.defaultDisplay.width
        val height: Int = windowManager.defaultDisplay.height
        val isLandscape = width > height
        if (isLandscape) setContentView(R.layout.horizontal_activity_main) else setContentView(R.layout.activity_main)
        setClickableToFilterButtons(false)
        if (savedInstanceState != null) {
            if (savedInstanceState.getParcelable<Bitmap>("currentPhoto") != null) {
                var bitmap = savedInstanceState.getParcelable<Bitmap>("currentPhoto")
                photoView.setImageBitmap(bitmap)
                bitmap = savedInstanceState.getParcelable("backUpPhoto")
                photoViewBackUp.setImageBitmap(bitmap)
                setClickableToFilterButtons(true)
                savePhotoButton.visibility = View.VISIBLE
                clearButton.visibility = View.VISIBLE;

            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (photoView.drawable != null) {
            var bitmap = photoView.drawable.toBitmap()
            outState.putParcelable("currentPhoto", bitmap)
            bitmap = photoViewBackUp.drawable.toBitmap()
            outState.putParcelable("backUpPhoto", bitmap)
        }
    }


    fun onAddPhotoButtonClick(view: View) {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*";
        startActivityForResult(photoPickerIntent, 1);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                try {
                    val imageUri = data?.data
                    val imageStream = imageUri?.let { contentResolver.openInputStream(it) }
                    val selectedImage = BitmapFactory.decodeStream(imageStream)
                    photoView.setImageBitmap(selectedImage)
                    photoViewBackUp.setImageBitmap(selectedImage)
                    addTitle.visibility = View.INVISIBLE
                    savePhotoButton.visibility = View.VISIBLE
                    clearButton.visibility = View.VISIBLE;
                    setClickableToFilterButtons(true)
                } catch (exception: FileNotFoundException) {
                    exception.printStackTrace();
                }
            }
        }
    }

    fun onSavePhotoButtonClick(view: View) {
        val bitmap = photoView.drawable.toBitmap()
        val currentDate = Date()
        MediaStore.Images.Media.insertImage(
            contentResolver, bitmap, currentDate.toString(),
            "created by SnapShots"
        )
        photoView.setImageResource(0);
        val addPhotoText = findViewById<TextView>(R.id.addTitle)
        addPhotoText.visibility = View.VISIBLE;
        val savePhotoBtm = findViewById<TextView>(R.id.savePhotoButton)
        setClickableToFilterButtons(false)
        savePhotoBtm.visibility = View.INVISIBLE;
        clearButton.visibility = View.INVISIBLE;
    }

    fun onClearButtonClick(view: View) {
        val bitmap = photoViewBackUp.drawable.toBitmap()
        photoView.setImageBitmap(bitmap)
    }

    fun onBlurButtonClick(view: View) {
        doFilterToMainImageView(BlurFilter())
    }

    fun onClarityButtonClick(view: View) {
        doFilterToMainImageView(ClarityFilter())
    }

    fun onReliefButtonClick(view: View) {
        doFilterToMainImageView(ReliefFilter())
    }

    fun onMedianButtonClick(view: View) {
        doFilterToMainImageView(MedianFilter())
    }

    fun onBuildUpFilterButtonClick(view: View) {
        doFilterToMainImageView(BuildUpFilter())
    }

    fun onErosionFilterButtonClick(view: View) {
        doFilterToMainImageView(ErosionFilter())
    }

    fun onUpLightButtonClick(view: View) {
        doFilterToMainImageView(UpLightFilter())
    }

    fun onDownLightButtonClick(view: View) {
        doFilterToMainImageView(DownLightFilter())
    }

    fun onUpSaturationButtonClick(view: View) {
        doFilterToMainImageView(UpSaturationFilter())
    }

    fun onDownSaturationButtonClick(view: View) {
        doFilterToMainImageView(DownSaturationFilter())
    }

    private fun doFilterToMainImageView(filter: BitmapFilter) {
        progressBar.visibility = ProgressBar.VISIBLE
        addPhotoButton.isClickable = false
        savePhotoButton.isClickable = false
        clearButton.isClickable = false
        setClickableToFilterButtons(false)
        thread(start = true) {
            var bitmap = photoView.drawable.toBitmap()
            bitmap = filter.filterBitmap(bitmap, enhance.isChecked)
            photoView.setImageBitmap(bitmap)
            progressBar.visibility = ProgressBar.INVISIBLE
            addPhotoButton.isClickable = true
            savePhotoButton.isClickable = true
            clearButton.isClickable = true
            setClickableToFilterButtons(true)
        }
    }

    private fun setClickableToFilterButtons(bool: Boolean) {
        BlurButton.isClickable = bool
        ClarityButton.isClickable = bool
        ReliefButton.isClickable = bool
        MedianButton.isClickable = bool
        ErosionButton.isClickable = bool
        BuildUpButton.isClickable = bool
        UpLightButton.isClickable = bool
        DownLightButton.isClickable = bool
    }
}
