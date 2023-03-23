package io.kaitai.struct.visualizer.example;

// This is a generated file! Please edit source .ksy file and use kaitai-struct-compiler to rebuild

import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.KaitaiStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Example extends KaitaiStruct {
    public Map<String, Integer> _attrStart = new HashMap<String, Integer>();
    public Map<String, Integer> _attrEnd = new HashMap<String, Integer>();
    public Map<String, ArrayList<Integer>> _arrStart = new HashMap<String, ArrayList<Integer>>();
    public Map<String, ArrayList<Integer>> _arrEnd = new HashMap<String, ArrayList<Integer>>();

    public static Example fromFile(String fileName) throws IOException {
        return new Example(new ByteBufferKaitaiStream(fileName));
    }
    public static String[] _seqFields = new String[] { "anInteger" };

    public Example(KaitaiStream _io) {
        this(_io, null, null);
    }

    public Example(KaitaiStream _io, KaitaiStruct _parent) {
        this(_io, _parent, null);
    }

    public Example(KaitaiStream _io, KaitaiStruct _parent, Example _root) {
        super(_io);
        this._parent = _parent;
        this._root = _root == null ? this : _root;
    }
    public void _read() {
        _attrStart.put("anInteger", this._io.pos());
        this.anInteger = this._io.readU1();
        _attrEnd.put("anInteger", this._io.pos());
    }
    private int anInteger;
    private Example _root;
    private KaitaiStruct _parent;
    public int anInteger() { return anInteger; }
    public Example _root() { return _root; }
    public KaitaiStruct _parent() { return _parent; }
}
