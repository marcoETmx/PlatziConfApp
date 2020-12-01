package com.marcoelizalde.platziconf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.marcoelizalde.platziconf.R
import com.marcoelizalde.platziconf.model.Speaker
import com.marcoelizalde.platziconf.view.adapter.SpeakerAdapter
import com.marcoelizalde.platziconf.view.adapter.SpeakerListener
import com.marcoelizalde.platziconf.viewmodel.SpeakersViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*
import androidx.navigation.fragment.findNavController


class SpeakersFragment : Fragment() , SpeakerListener {

    private lateinit var speakerAdapter: SpeakerAdapter

    private lateinit var viewModel: SpeakersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this)
                .get(SpeakersViewModel::class.java)

        viewModel.refresh()

        speakerAdapter = SpeakerAdapter(this)

        rvSpeakers.apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = speakerAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.listSpeakers.observe(viewLifecycleOwner,
            Observer<List<Speaker>> { speakers ->
                speakerAdapter.updateData(speakers)
            })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
            if (it != null)
                rlBaseSpeaker.visibility = View.INVISIBLE
        })
    }

    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        val bundle = bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.speakersDetailFragmentDialog, bundle)
    }

}