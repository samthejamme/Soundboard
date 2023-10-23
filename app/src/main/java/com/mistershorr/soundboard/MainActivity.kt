package com.mistershorr.soundboard

import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.mistershorr.soundboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    lateinit var soundPool : SoundPool
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
        setContentView(R.layout.activity_main)

        initializeSoundPool()
        setListeners()
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
    }

    private fun initializeSoundPool() {

        this.volumeControlStream = AudioManager.STREAM_MUSIC
        soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
//        soundPool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
//           // isSoundPoolLoaded = true
//        })
        aNote = soundPool.load(this, R.raw.scalea, 1)
        // Maps use key-value pairs (just like the Bundle)
        noteMap["A"] = aNote
        hANote = soundPool.load(this, R.raw.scalehigha, 1)
        noteMap["HA"] = hANote
        bbNote = soundPool.load(this, R.raw.scalebb, 1)
        noteMap["Bb"] = bbNote
        hBbNote = soundPool.load(this, R.raw.scalehighbb, 1)
        noteMap["HBb"] = hBbNote
        bNote = soundPool.load(this, R.raw.scaleb, 1)
        // kotlin lets you use array-like assignments
        noteMap["B"] = bNote
        hBNote = soundPool.load(this, R.raw.scalehighb, 1)
        noteMap["HB"] = hBNote
        cNote =  soundPool.load(this, R.raw.scalec, 1)
        noteMap["C"] = cNote
        hCNote = soundPool.load(this, R.raw.scalehighc, 1)
        noteMap["HC"] = hCNote
        csNote = soundPool.load(this, R.raw.scalecs, 1)
        noteMap["C#"] = csNote
        hCsNote = soundPool.load(this, R.raw.scalehighcs, 1)
        noteMap["HC#"] = hCsNote
        dNote =  soundPool.load(this, R.raw.scaled, 1)
        noteMap["D"] = dNote
        hDNote = soundPool.load(this, R.raw.scalehighd, 1)
        noteMap["HD"] = hDNote
        dsNote =  soundPool.load(this, R.raw.scaleds, 1)
        noteMap["D#"] = dsNote
        hDsNote = soundPool.load(this, R.raw.scalehighds, 1)
        noteMap["HD#"] = hDsNote
        eNote = soundPool.load(this, R.raw.scalee, 1)
        noteMap["E"] = eNote
        hENote = soundPool.load(this, R.raw.scalehighe, 1)
        noteMap["HE"] = hENote
        fNote = soundPool.load(this, R.raw.scalef, 1)
        noteMap["F"] = fNote
        hFNote = soundPool.load(this, R.raw.scalehighf, 1)
        noteMap["HF"] = hFNote
        fsNote = soundPool.load(this, R.raw.scalefs, 1)
        noteMap["F#"] = fsNote
        hFsNote = soundPool.load(this, R.raw.scalehighfs, 1)
        noteMap["HF#"] = hFsNote
        gNote =  soundPool.load(this, R.raw.scaleg, 1)
        noteMap["G"] = gNote
        hGNote = soundPool.load(this, R.raw.scalehighg, 1)
        noteMap["HG"] = hGNote
        lGNote = soundPool.load(this, R.raw.scalelowg, 1)
        noteMap["LG"] = lGNote
        gsNote =  soundPool.load(this, R.raw.scalegs, 1)
        noteMap["G#"] = gsNote
        hGsNote = soundPool.load(this, R.raw.scalehighgs, 1)
        noteMap["HG#"] = hGsNote
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
            }
        }
    }

    private fun playNote(note: String) {
        // ?: is the elvis operator. it lets you provide a default value
        // if the value is null
        playNote(noteMap[note] ?: 0)
    }

    private fun playSong(song: List<Note>) {
        // loop through a list of notes
        var int = 0
        while(int <= song.lastIndex) {
            // play the note
            // note you get is a string
            // to play the note, you need the corresponding soundPool object
            when(song.get(int).note){
                "A" -> playNote(aNote)
                "Bb" -> playNote(bbNote)
                "B" -> playNote(bNote)
                "C" -> playNote(cNote)
                "C#" -> playNote(csNote)
                "D" -> playNote(dNote)
                "D#" -> playNote(dsNote)
                "E" -> playNote(eNote)
                "F" -> playNote(fNote)
                "F#" -> playNote(fsNote)
                "G" -> playNote(gNote)
                "G#" -> playNote(gsNote)
                "LG" -> playNote(lGNote)
                "HG" -> playNote(lGNote)
                "HA" -> playNote(lGNote)
                "HB" -> playNote(lGNote)
                "Hb" -> playNote(lGNote)
                "HBb" -> playNote(lGNote)
                "HC#" -> playNote(lGNote)
                "HD" -> playNote(lGNote)
                "HD#" -> playNote(lGNote)
                "HE" -> playNote(lGNote)
                "HF" -> playNote(lGNote)
                "HF#" -> playNote(lGNote)
                "HGb" -> playNote(lGNote)
            }
            int++
        }
    }

    private fun delay(time: Long) {
        try{
            Thread.sleep(time)
        } catch(e: InterruptedException) {
            e.printStackTrace()
        }
    }
}