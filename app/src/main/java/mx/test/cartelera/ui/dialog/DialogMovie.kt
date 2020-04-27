package mx.test.cartelera.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import mx.test.cartelera.R
import mx.test.cartelera.utils.BASE_IMAGE_URL

class DialogMovie : DialogFragment() {
    private var url: String? = null
    private var rating: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (arguments != null)
        {
            url = arguments?.getString("URL")
            rating = arguments?.getString("RATING")
        }
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_movie, container, false)
    }

    override fun onViewCreated(view:View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnDone = view.findViewById<Button>(R.id.btn_cerrar)
        val tvRating = view.findViewById<TextView>(R.id.textView_rating)
        val image = view.findViewById<ImageView>(R.id.imageView_dialog)

        tvRating.setText(rating)
        Glide.with(this).load(BASE_IMAGE_URL.plus(url)).into(image)

        btnDone.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view:View) {
                dismiss()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar)
    }
}