package com.mistershorr.soundboard

import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mistershorr.soundboard.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var soundPool : SoundPool
    private lateinit var listOfNotes : List<Note>
    var aNote = 0; var hANote = 0
    var bNote = 0; var bbNote = 0
    var hBNote = 0; var hBbNote = 0
    var cNote = 0; var csNote = 0
    var hCNote = 0; var hCsNote = 0
    var dNote = 0; var dsNote = 0
    var hDNote = 0; var hDsNote = 0
    var eNote = 0; var hENote = 0
    var fNote = 0; var fsNote = 0
    var hFNote = 0; var hFsNote = 0
    var gNote = 0; var gsNote = 0
    var hGNote = 0; var hGsNote = 0
    var lGNote = 0
    var noteMap = HashMap<String, Int>()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeSoundPool()
        setListeners()
        loadSong()
    }

    private fun setListeners() {
        val soundBoardListener = SoundBoardListener()
        binding.buttonMainA.setOnClickListener(soundBoardListener)
        binding.buttonMainBFlat.setOnClickListener(soundBoardListener)
        binding.buttonMainB.setOnClickListener(soundBoardListener)
        binding.buttonMainC.setOnClickListener(soundBoardListener)
        binding.buttonMainCSharp.setOnClickListener(soundBoardListener)
        binding.buttonMainD.setOnClickListener(soundBoardListener)
        binding.buttonMainDSharp.setOnClickListener(soundBoardListener)
        binding.buttonMainE.setOnClickListener(soundBoardListener)
        binding.buttonMainF.setOnClickListener(soundBoardListener)
        binding.buttonMainFSharp.setOnClickListener(soundBoardListener)
        binding.buttonMainG.setOnClickListener(soundBoardListener)
        binding.buttonMainGSharp.setOnClickListener(soundBoardListener)
        binding.buttonMainPlaySong.setOnClickListener(soundBoardListener)
    }

    private fun initializeSoundPool() {

        this.volumeControlStream = AudioManager.STREAM_MUSIC
        soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
//        soundPool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
//           // isSoundPoolLoaded = true
//        })
        aNote = soundPool.load(this, R.raw.scalea, 1)
        hANote = soundPool.load(this, R.raw.scalehigha, 1)
        bbNote = soundPool.load(this, R.raw.scalebb, 1)
        hBbNote = soundPool.load(this, R.raw.scalehighbb, 1)
        bNote = soundPool.load(this, R.raw.scaleb, 1)
        hBNote = soundPool.load(this, R.raw.scalehighb, 1)
        cNote = soundPool.load(this, R.raw.scalec, 1)
        hCNote = soundPool.load(this, R.raw.scalehighc, 1)
        csNote = soundPool.load(this, R.raw.scalecs, 1)
        hCsNote = soundPool.load(this, R.raw.scalehighcs, 1)
        dNote = soundPool.load(this, R.raw.scaled, 1)
        hDNote = soundPool.load(this, R.raw.scalehighd, 1)
        dsNote = soundPool.load(this, R.raw.scaleds, 1)
        hDsNote = soundPool.load(this, R.raw.scalehighds, 1)
        eNote = soundPool.load(this, R.raw.scalee, 1)
        hENote = soundPool.load(this, R.raw.scalehighe, 1)
        fNote = soundPool.load(this, R.raw.scalef, 1)
        hFNote = soundPool.load(this, R.raw.scalehighf, 1)
        fsNote = soundPool.load(this, R.raw.scalefs, 1)
        hFsNote = soundPool.load(this, R.raw.scalehighfs, 1)
        gNote = soundPool.load(this, R.raw.scaleg, 1)
        hGNote = soundPool.load(this, R.raw.scalehighg, 1)
        lGNote = soundPool.load(this, R.raw.scalelowg, 1)
        gsNote = soundPool.load(this, R.raw.scalegs, 1)
        hGsNote = soundPool.load(this, R.raw.scalehighgs, 1)
        noteMap["A"] = aNote
        noteMap["HA"] = hANote
        noteMap["Bb"] = bbNote
        noteMap["HBb"] = hBbNote
        noteMap["B"] = bNote
        noteMap["HB"] = hBNote
        noteMap["C"] = cNote
        noteMap["HC"] = hCNote
        noteMap["C#"] = csNote
        noteMap["HC#"] = hCsNote
        noteMap["D"] = dNote
        noteMap["HD"] = hDNote
        noteMap["D#"] = dsNote
        noteMap["HD#"] = hDsNote
        noteMap["E"] = eNote
        noteMap["HE"] = hENote
        noteMap["F"] = fNote
        noteMap["HF"] = hFNote
        noteMap["F#"] = fsNote
        noteMap["HF#"] = hFsNote
        noteMap["G"] = gNote
        noteMap["HG"] = hGNote
        noteMap["LG"] = lGNote
        noteMap["G#"] = gsNote
        noteMap["HG#"] = hGsNote
    }

    private fun playNote(note: String) {
        // ?: is the elvis operator. it lets you provide a default value
        // if the value is null
        playNote(noteMap[note] ?: 0)
    }
    private fun playNote(noteId : Int) {
        soundPool.play(noteId, 1f, 1f, 1, 0, 1f)
    }

    private inner class SoundBoardListener : View.OnClickListener {
        override fun onClick(v: View?) {
            when(v?.id) {
                R.id.button_main_a -> playNote(aNote); R.id.button_main_e -> playNote(eNote)
                R.id.button_main_b -> playNote(bNote); R.id.button_main_b_flat -> playNote(bbNote)
                R.id.button_main_c -> playNote(cNote); R.id.button_main_c_sharp -> playNote(csNote)
                R.id.button_main_d -> playNote(dNote); R.id.button_main_d_sharp -> playNote(dsNote)
                R.id.button_main_f -> playNote(fNote); R.id.button_main_f_sharp -> playNote(fsNote)
                R.id.button_main_g -> playNote(gNote); R.id.button_main_g_sharp -> playNote(gsNote)
                R.id.button_main_playSong -> GlobalScope.launch {
                    playSong(listOfNotes)
                }

            }
        }
    }

    private fun loadSong() {
        val inputStream = resources.openRawResource(R.raw.bachtwopartinventioninfmajor)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }

        // parse json quesitons
        val gson = Gson()
        val sType = object: TypeToken<List<Note>>() {}.type
        val notes = gson.fromJson<List<Note>>(jsonString, sType)
        listOfNotes = notes
    }

    private fun delay(time: Long) {
        try {
            Thread.sleep(time)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private suspend fun playSong(notes : List<Note>) {
        GlobalScope.launch(Dispatchers.Main) {
            binding.groupMainNoteButtons.referencedIds.forEach {
                findViewById<Button>(it).isEnabled = false
            }
        }
        for(note in notes) {
            playNote(noteMap[note.note] ?: 0)
            delay(note.duration)
        }
        GlobalScope.launch(Dispatchers.Main) {
            binding.groupMainNoteButtons.referencedIds.forEach {
                findViewById<Button>(it).isEnabled = true
            }
        }
    }
}