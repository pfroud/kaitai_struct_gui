// This is a generated file! Please edit source .ksy file and use kaitai-struct-compiler to rebuild

package compiled_from_ksy;

import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.KaitaiStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.nio.charset.Charset;
import java.util.Arrays;

public class Basic extends KaitaiStruct {
    public Map<String, Integer> _attrStart = new HashMap<String, Integer>();
    public Map<String, Integer> _attrEnd = new HashMap<String, Integer>();
    public Map<String, ArrayList<Integer>> _arrStart = new HashMap<String, ArrayList<Integer>>();
    public Map<String, ArrayList<Integer>> _arrEnd = new HashMap<String, ArrayList<Integer>>();

    public static Basic fromFile(String fileName) throws IOException {
        return new Basic(new ByteBufferKaitaiStream(fileName));
    }

    public enum MyEnum {
        ONE(1),
        TWO(2);

        private final long id;
        MyEnum(long id) { this.id = id; }
        public long id() { return id; }
        private static final Map<Long, MyEnum> byId = new HashMap<Long, MyEnum>(2);
        static {
            for (MyEnum e : MyEnum.values())
                byId.put(e.id(), e);
        }
        public static MyEnum byId(long id) { return byId.get(id); }
    }
    public static String[] _seqFields = new String[] { "sequentialBytesScalar", "sequentialBytesList", "sequentialIntegerScalar", "sequentialIntegerList", "sequentialFloatScalar", "sequentialFloatList", "sequentialEnumScalar", "sequentialEnumList", "sequentialBooleanScalar", "sequentialBooleanList", "sequentialStringScalar", "sequentialStringList", "sequentialStructScalar", "sequentialStructList" };

    public Basic(KaitaiStream _io) {
        this(_io, null, null);
    }

    public Basic(KaitaiStream _io, KaitaiStruct _parent) {
        this(_io, _parent, null);
    }

    public Basic(KaitaiStream _io, KaitaiStruct _parent, Basic _root) {
        super(_io);
        this._parent = _parent;
        this._root = _root == null ? this : _root;
        _read();
    }
    private void _read() {
        _attrStart.put("sequentialBytesScalar", this._io.pos());
        this.sequentialBytesScalar = this._io.readBytes(2);
        _attrEnd.put("sequentialBytesScalar", this._io.pos());
        _attrStart.put("sequentialBytesList", this._io.pos());
        sequentialBytesList = new ArrayList<byte[]>(((Number) (2)).intValue());
        for (int i = 0; i < 2; i++) {
            {
                ArrayList<Integer> _posList = _arrStart.get("sequentialBytesList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrStart.put("sequentialBytesList", _posList);
                }
                _posList.add(this._io.pos());
            }
            this.sequentialBytesList.add(this._io.readBytes(2));
            {
                ArrayList<Integer> _posList = _arrEnd.get("sequentialBytesList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrEnd.put("sequentialBytesList", _posList);
                }
                _posList.add(this._io.pos());
            }
        }
        _attrEnd.put("sequentialBytesList", this._io.pos());
        _attrStart.put("sequentialIntegerScalar", this._io.pos());
        this.sequentialIntegerScalar = this._io.readU1();
        _attrEnd.put("sequentialIntegerScalar", this._io.pos());
        _attrStart.put("sequentialIntegerList", this._io.pos());
        sequentialIntegerList = new ArrayList<Integer>(((Number) (2)).intValue());
        for (int i = 0; i < 2; i++) {
            {
                ArrayList<Integer> _posList = _arrStart.get("sequentialIntegerList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrStart.put("sequentialIntegerList", _posList);
                }
                _posList.add(this._io.pos());
            }
            this.sequentialIntegerList.add(this._io.readU1());
            {
                ArrayList<Integer> _posList = _arrEnd.get("sequentialIntegerList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrEnd.put("sequentialIntegerList", _posList);
                }
                _posList.add(this._io.pos());
            }
        }
        _attrEnd.put("sequentialIntegerList", this._io.pos());
        _attrStart.put("sequentialFloatScalar", this._io.pos());
        this.sequentialFloatScalar = this._io.readF4le();
        _attrEnd.put("sequentialFloatScalar", this._io.pos());
        _attrStart.put("sequentialFloatList", this._io.pos());
        sequentialFloatList = new ArrayList<Float>(((Number) (2)).intValue());
        for (int i = 0; i < 2; i++) {
            {
                ArrayList<Integer> _posList = _arrStart.get("sequentialFloatList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrStart.put("sequentialFloatList", _posList);
                }
                _posList.add(this._io.pos());
            }
            this.sequentialFloatList.add(this._io.readF4le());
            {
                ArrayList<Integer> _posList = _arrEnd.get("sequentialFloatList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrEnd.put("sequentialFloatList", _posList);
                }
                _posList.add(this._io.pos());
            }
        }
        _attrEnd.put("sequentialFloatList", this._io.pos());
        _attrStart.put("sequentialEnumScalar", this._io.pos());
        this.sequentialEnumScalar = MyEnum.byId(this._io.readU1());
        _attrEnd.put("sequentialEnumScalar", this._io.pos());
        _attrStart.put("sequentialEnumList", this._io.pos());
        sequentialEnumList = new ArrayList<MyEnum>(((Number) (2)).intValue());
        for (int i = 0; i < 2; i++) {
            {
                ArrayList<Integer> _posList = _arrStart.get("sequentialEnumList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrStart.put("sequentialEnumList", _posList);
                }
                _posList.add(this._io.pos());
            }
            this.sequentialEnumList.add(MyEnum.byId(this._io.readU1()));
            {
                ArrayList<Integer> _posList = _arrEnd.get("sequentialEnumList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrEnd.put("sequentialEnumList", _posList);
                }
                _posList.add(this._io.pos());
            }
        }
        _attrEnd.put("sequentialEnumList", this._io.pos());
        _attrStart.put("sequentialBooleanScalar", this._io.pos());
        this.sequentialBooleanScalar = this._io.readBitsIntBe(1) != 0;
        _attrEnd.put("sequentialBooleanScalar", this._io.pos());
        _attrStart.put("sequentialBooleanList", this._io.pos());
        sequentialBooleanList = new ArrayList<Boolean>(((Number) (2)).intValue());
        for (int i = 0; i < 2; i++) {
            {
                ArrayList<Integer> _posList = _arrStart.get("sequentialBooleanList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrStart.put("sequentialBooleanList", _posList);
                }
                _posList.add(this._io.pos());
            }
            this.sequentialBooleanList.add(this._io.readBitsIntBe(1) != 0);
            {
                ArrayList<Integer> _posList = _arrEnd.get("sequentialBooleanList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrEnd.put("sequentialBooleanList", _posList);
                }
                _posList.add(this._io.pos());
            }
        }
        _attrEnd.put("sequentialBooleanList", this._io.pos());
        this._io.alignToByte();
        _attrStart.put("sequentialStringScalar", this._io.pos());
        this.sequentialStringScalar = new String(this._io.readBytes(2), Charset.forName("UTF-8"));
        _attrEnd.put("sequentialStringScalar", this._io.pos());
        _attrStart.put("sequentialStringList", this._io.pos());
        sequentialStringList = new ArrayList<String>(((Number) (2)).intValue());
        for (int i = 0; i < 2; i++) {
            {
                ArrayList<Integer> _posList = _arrStart.get("sequentialStringList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrStart.put("sequentialStringList", _posList);
                }
                _posList.add(this._io.pos());
            }
            this.sequentialStringList.add(new String(this._io.readBytes(2), Charset.forName("UTF-8")));
            {
                ArrayList<Integer> _posList = _arrEnd.get("sequentialStringList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrEnd.put("sequentialStringList", _posList);
                }
                _posList.add(this._io.pos());
            }
        }
        _attrEnd.put("sequentialStringList", this._io.pos());
        _attrStart.put("sequentialStructScalar", this._io.pos());
        this.sequentialStructScalar = new MyType(this._io, this, _root);
        _attrEnd.put("sequentialStructScalar", this._io.pos());
        _attrStart.put("sequentialStructList", this._io.pos());
        sequentialStructList = new ArrayList<MyType>(((Number) (2)).intValue());
        for (int i = 0; i < 2; i++) {
            {
                ArrayList<Integer> _posList = _arrStart.get("sequentialStructList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrStart.put("sequentialStructList", _posList);
                }
                _posList.add(this._io.pos());
            }
            this.sequentialStructList.add(new MyType(this._io, this, _root));
            {
                ArrayList<Integer> _posList = _arrEnd.get("sequentialStructList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrEnd.put("sequentialStructList", _posList);
                }
                _posList.add(this._io.pos());
            }
        }
        _attrEnd.put("sequentialStructList", this._io.pos());
    }
    public static class MyType extends KaitaiStruct {
        public Map<String, Integer> _attrStart = new HashMap<String, Integer>();
        public Map<String, Integer> _attrEnd = new HashMap<String, Integer>();
        public Map<String, ArrayList<Integer>> _arrStart = new HashMap<String, ArrayList<Integer>>();
        public Map<String, ArrayList<Integer>> _arrEnd = new HashMap<String, ArrayList<Integer>>();

        public static MyType fromFile(String fileName) throws IOException {
            return new MyType(new ByteBufferKaitaiStream(fileName));
        }
        public static String[] _seqFields = new String[] {  };

        public MyType(KaitaiStream _io) {
            this(_io, null, null);
        }

        public MyType(KaitaiStream _io, Basic _parent) {
            this(_io, _parent, null);
        }

        public MyType(KaitaiStream _io, Basic _parent, Basic _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
        }
        private Basic _root;
        private Basic _parent;
        public Basic _root() { return _root; }
        public Basic _parent() { return _parent; }
    }
    private Float chunkInstanceFloatScalar;
    public Float chunkInstanceFloatScalar() {
        if (this.chunkInstanceFloatScalar != null)
            return this.chunkInstanceFloatScalar;
        _attrStart.put("chunkInstanceFloatScalar", this._io.pos());
        this.chunkInstanceFloatScalar = this._io.readF4le();
        _attrEnd.put("chunkInstanceFloatScalar", this._io.pos());
        return this.chunkInstanceFloatScalar;
    }
    private ArrayList<String> chunkInstanceStringList;
    public ArrayList<String> chunkInstanceStringList() {
        if (this.chunkInstanceStringList != null)
            return this.chunkInstanceStringList;
        _attrStart.put("chunkInstanceStringList", this._io.pos());
        chunkInstanceStringList = new ArrayList<String>(((Number) (2)).intValue());
        for (int i = 0; i < 2; i++) {
            {
                ArrayList<Integer> _posList = _arrStart.get("chunkInstanceStringList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrStart.put("chunkInstanceStringList", _posList);
                }
                _posList.add(this._io.pos());
            }
            this.chunkInstanceStringList.add(new String(this._io.readBytes(2), Charset.forName("UTF-8")));
            {
                ArrayList<Integer> _posList = _arrEnd.get("chunkInstanceStringList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrEnd.put("chunkInstanceStringList", _posList);
                }
                _posList.add(this._io.pos());
            }
        }
        _attrEnd.put("chunkInstanceStringList", this._io.pos());
        return this.chunkInstanceStringList;
    }
    private ArrayList<MyType> valueInstanceStructList;
    public ArrayList<MyType> valueInstanceStructList() {
        if (this.valueInstanceStructList != null)
            return this.valueInstanceStructList;
        this.valueInstanceStructList = new ArrayList<MyType>(Arrays.asList(sequentialStructScalar(), sequentialStructScalar()));
        return this.valueInstanceStructList;
    }
    private ArrayList<byte[]> chunkInstanceBytesList;
    public ArrayList<byte[]> chunkInstanceBytesList() {
        if (this.chunkInstanceBytesList != null)
            return this.chunkInstanceBytesList;
        _attrStart.put("chunkInstanceBytesList", this._io.pos());
        chunkInstanceBytesList = new ArrayList<byte[]>(((Number) (2)).intValue());
        for (int i = 0; i < 2; i++) {
            {
                ArrayList<Integer> _posList = _arrStart.get("chunkInstanceBytesList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrStart.put("chunkInstanceBytesList", _posList);
                }
                _posList.add(this._io.pos());
            }
            this.chunkInstanceBytesList.add(this._io.readBytes(2));
            {
                ArrayList<Integer> _posList = _arrEnd.get("chunkInstanceBytesList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrEnd.put("chunkInstanceBytesList", _posList);
                }
                _posList.add(this._io.pos());
            }
        }
        _attrEnd.put("chunkInstanceBytesList", this._io.pos());
        return this.chunkInstanceBytesList;
    }
    private ArrayList<String> valueInstanceStringList;
    public ArrayList<String> valueInstanceStringList() {
        if (this.valueInstanceStringList != null)
            return this.valueInstanceStringList;
        this.valueInstanceStringList = new ArrayList<String>(Arrays.asList("hello", "world"));
        return this.valueInstanceStringList;
    }
    private ArrayList<Integer> chunkInstanceIntegerList;
    public ArrayList<Integer> chunkInstanceIntegerList() {
        if (this.chunkInstanceIntegerList != null)
            return this.chunkInstanceIntegerList;
        _attrStart.put("chunkInstanceIntegerList", this._io.pos());
        chunkInstanceIntegerList = new ArrayList<Integer>(((Number) (2)).intValue());
        for (int i = 0; i < 2; i++) {
            {
                ArrayList<Integer> _posList = _arrStart.get("chunkInstanceIntegerList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrStart.put("chunkInstanceIntegerList", _posList);
                }
                _posList.add(this._io.pos());
            }
            this.chunkInstanceIntegerList.add(this._io.readU1());
            {
                ArrayList<Integer> _posList = _arrEnd.get("chunkInstanceIntegerList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrEnd.put("chunkInstanceIntegerList", _posList);
                }
                _posList.add(this._io.pos());
            }
        }
        _attrEnd.put("chunkInstanceIntegerList", this._io.pos());
        return this.chunkInstanceIntegerList;
    }
    private MyType valueInstanceStructScalar;
    public MyType valueInstanceStructScalar() {
        if (this.valueInstanceStructScalar != null)
            return this.valueInstanceStructScalar;
        this.valueInstanceStructScalar = sequentialStructScalar();
        return this.valueInstanceStructScalar;
    }
    private MyEnum chunkInstanceEnumScalar;
    public MyEnum chunkInstanceEnumScalar() {
        if (this.chunkInstanceEnumScalar != null)
            return this.chunkInstanceEnumScalar;
        _attrStart.put("chunkInstanceEnumScalar", this._io.pos());
        this.chunkInstanceEnumScalar = MyEnum.byId(this._io.readU1());
        _attrEnd.put("chunkInstanceEnumScalar", this._io.pos());
        return this.chunkInstanceEnumScalar;
    }
    private Byte valueInstanceIntegerScalar;
    public Byte valueInstanceIntegerScalar() {
        if (this.valueInstanceIntegerScalar != null)
            return this.valueInstanceIntegerScalar;
        byte _tmp = (byte) (1);
        this.valueInstanceIntegerScalar = _tmp;
        return this.valueInstanceIntegerScalar;
    }
    private ArrayList<Integer> valueInstanceIntegerList;
    public ArrayList<Integer> valueInstanceIntegerList() {
        if (this.valueInstanceIntegerList != null)
            return this.valueInstanceIntegerList;
        this.valueInstanceIntegerList = new ArrayList<Integer>(Arrays.asList(300, 301));
        return this.valueInstanceIntegerList;
    }
    private ArrayList<MyType> chunkInstanceStructList;
    public ArrayList<MyType> chunkInstanceStructList() {
        if (this.chunkInstanceStructList != null)
            return this.chunkInstanceStructList;
        _attrStart.put("chunkInstanceStructList", this._io.pos());
        chunkInstanceStructList = new ArrayList<MyType>(((Number) (2)).intValue());
        for (int i = 0; i < 2; i++) {
            {
                ArrayList<Integer> _posList = _arrStart.get("chunkInstanceStructList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrStart.put("chunkInstanceStructList", _posList);
                }
                _posList.add(this._io.pos());
            }
            this.chunkInstanceStructList.add(new MyType(this._io, this, _root));
            {
                ArrayList<Integer> _posList = _arrEnd.get("chunkInstanceStructList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrEnd.put("chunkInstanceStructList", _posList);
                }
                _posList.add(this._io.pos());
            }
        }
        _attrEnd.put("chunkInstanceStructList", this._io.pos());
        return this.chunkInstanceStructList;
    }
    private byte[] chunkInstanceBytesScalar;
    public byte[] chunkInstanceBytesScalar() {
        if (this.chunkInstanceBytesScalar != null)
            return this.chunkInstanceBytesScalar;
        _attrStart.put("chunkInstanceBytesScalar", this._io.pos());
        this.chunkInstanceBytesScalar = this._io.readBytes(2);
        _attrEnd.put("chunkInstanceBytesScalar", this._io.pos());
        return this.chunkInstanceBytesScalar;
    }
    private String valueInstanceStringScalar;
    public String valueInstanceStringScalar() {
        if (this.valueInstanceStringScalar != null)
            return this.valueInstanceStringScalar;
        this.valueInstanceStringScalar = "hello";
        return this.valueInstanceStringScalar;
    }
    private ArrayList<byte[]> valueInstanceBytesList;
    public ArrayList<byte[]> valueInstanceBytesList() {
        if (this.valueInstanceBytesList != null)
            return this.valueInstanceBytesList;
        this.valueInstanceBytesList = new ArrayList<byte[]>(Arrays.asList(new byte[] { 0, 1 }, new byte[] { 2, 3 }));
        return this.valueInstanceBytesList;
    }
    private byte[] valueInstanceBytesScalar;
    public byte[] valueInstanceBytesScalar() {
        if (this.valueInstanceBytesScalar != null)
            return this.valueInstanceBytesScalar;
        this.valueInstanceBytesScalar = new byte[] { 0, 1 };
        return this.valueInstanceBytesScalar;
    }
    private ArrayList<MyEnum> valueInstanceEnumList;
    public ArrayList<MyEnum> valueInstanceEnumList() {
        if (this.valueInstanceEnumList != null)
            return this.valueInstanceEnumList;
        this.valueInstanceEnumList = new ArrayList<MyEnum>(Arrays.asList(MyEnum.ONE, MyEnum.TWO));
        return this.valueInstanceEnumList;
    }
    private MyEnum valueInstanceEnumScalar;
    public MyEnum valueInstanceEnumScalar() {
        if (this.valueInstanceEnumScalar != null)
            return this.valueInstanceEnumScalar;
        this.valueInstanceEnumScalar = MyEnum.ONE;
        return this.valueInstanceEnumScalar;
    }
    private ArrayList<MyEnum> chunkInstanceEnumList;
    public ArrayList<MyEnum> chunkInstanceEnumList() {
        if (this.chunkInstanceEnumList != null)
            return this.chunkInstanceEnumList;
        _attrStart.put("chunkInstanceEnumList", this._io.pos());
        chunkInstanceEnumList = new ArrayList<MyEnum>(((Number) (2)).intValue());
        for (int i = 0; i < 2; i++) {
            {
                ArrayList<Integer> _posList = _arrStart.get("chunkInstanceEnumList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrStart.put("chunkInstanceEnumList", _posList);
                }
                _posList.add(this._io.pos());
            }
            this.chunkInstanceEnumList.add(MyEnum.byId(this._io.readU1()));
            {
                ArrayList<Integer> _posList = _arrEnd.get("chunkInstanceEnumList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrEnd.put("chunkInstanceEnumList", _posList);
                }
                _posList.add(this._io.pos());
            }
        }
        _attrEnd.put("chunkInstanceEnumList", this._io.pos());
        return this.chunkInstanceEnumList;
    }
    private ArrayList<Boolean> valueInstanceBooleanList;
    public ArrayList<Boolean> valueInstanceBooleanList() {
        if (this.valueInstanceBooleanList != null)
            return this.valueInstanceBooleanList;
        this.valueInstanceBooleanList = new ArrayList<Boolean>(Arrays.asList(true, false));
        return this.valueInstanceBooleanList;
    }
    private Boolean valueInstanceBooleanScalar;
    public Boolean valueInstanceBooleanScalar() {
        if (this.valueInstanceBooleanScalar != null)
            return this.valueInstanceBooleanScalar;
        boolean _tmp = (boolean) (true);
        this.valueInstanceBooleanScalar = _tmp;
        return this.valueInstanceBooleanScalar;
    }
    private MyType chunkInstanceStructScalar;
    public MyType chunkInstanceStructScalar() {
        if (this.chunkInstanceStructScalar != null)
            return this.chunkInstanceStructScalar;
        _attrStart.put("chunkInstanceStructScalar", this._io.pos());
        this.chunkInstanceStructScalar = new MyType(this._io, this, _root);
        _attrEnd.put("chunkInstanceStructScalar", this._io.pos());
        return this.chunkInstanceStructScalar;
    }
    private Boolean chunkInstanceBooleanScalar;
    public Boolean chunkInstanceBooleanScalar() {
        if (this.chunkInstanceBooleanScalar != null)
            return this.chunkInstanceBooleanScalar;
        _attrStart.put("chunkInstanceBooleanScalar", this._io.pos());
        this.chunkInstanceBooleanScalar = this._io.readBitsIntBe(1) != 0;
        _attrEnd.put("chunkInstanceBooleanScalar", this._io.pos());
        return this.chunkInstanceBooleanScalar;
    }
    private Double valueInstanceFloatScalar;
    public Double valueInstanceFloatScalar() {
        if (this.valueInstanceFloatScalar != null)
            return this.valueInstanceFloatScalar;
        double _tmp = (double) (0.1);
        this.valueInstanceFloatScalar = _tmp;
        return this.valueInstanceFloatScalar;
    }
    private Integer chunkInstanceIntegerScalar;
    public Integer chunkInstanceIntegerScalar() {
        if (this.chunkInstanceIntegerScalar != null)
            return this.chunkInstanceIntegerScalar;
        _attrStart.put("chunkInstanceIntegerScalar", this._io.pos());
        this.chunkInstanceIntegerScalar = this._io.readU1();
        _attrEnd.put("chunkInstanceIntegerScalar", this._io.pos());
        return this.chunkInstanceIntegerScalar;
    }
    private ArrayList<Boolean> chunkInstanceBooleanList;
    public ArrayList<Boolean> chunkInstanceBooleanList() {
        if (this.chunkInstanceBooleanList != null)
            return this.chunkInstanceBooleanList;
        _attrStart.put("chunkInstanceBooleanList", this._io.pos());
        chunkInstanceBooleanList = new ArrayList<Boolean>(((Number) (2)).intValue());
        for (int i = 0; i < 2; i++) {
            {
                ArrayList<Integer> _posList = _arrStart.get("chunkInstanceBooleanList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrStart.put("chunkInstanceBooleanList", _posList);
                }
                _posList.add(this._io.pos());
            }
            this.chunkInstanceBooleanList.add(this._io.readBitsIntBe(1) != 0);
            {
                ArrayList<Integer> _posList = _arrEnd.get("chunkInstanceBooleanList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrEnd.put("chunkInstanceBooleanList", _posList);
                }
                _posList.add(this._io.pos());
            }
        }
        _attrEnd.put("chunkInstanceBooleanList", this._io.pos());
        return this.chunkInstanceBooleanList;
    }
    private String chunkInstanceStringScalar;
    public String chunkInstanceStringScalar() {
        if (this.chunkInstanceStringScalar != null)
            return this.chunkInstanceStringScalar;
        _attrStart.put("chunkInstanceStringScalar", this._io.pos());
        this.chunkInstanceStringScalar = new String(this._io.readBytes(2), Charset.forName("UTF-8"));
        _attrEnd.put("chunkInstanceStringScalar", this._io.pos());
        return this.chunkInstanceStringScalar;
    }
    private ArrayList<Double> valueInstanceFloatList;
    public ArrayList<Double> valueInstanceFloatList() {
        if (this.valueInstanceFloatList != null)
            return this.valueInstanceFloatList;
        this.valueInstanceFloatList = new ArrayList<Double>(Arrays.asList(0.1, 0.2));
        return this.valueInstanceFloatList;
    }
    private ArrayList<Float> chunkInstanceFloatList;
    public ArrayList<Float> chunkInstanceFloatList() {
        if (this.chunkInstanceFloatList != null)
            return this.chunkInstanceFloatList;
        _attrStart.put("chunkInstanceFloatList", this._io.pos());
        chunkInstanceFloatList = new ArrayList<Float>(((Number) (2)).intValue());
        for (int i = 0; i < 2; i++) {
            {
                ArrayList<Integer> _posList = _arrStart.get("chunkInstanceFloatList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrStart.put("chunkInstanceFloatList", _posList);
                }
                _posList.add(this._io.pos());
            }
            this.chunkInstanceFloatList.add(this._io.readF4le());
            {
                ArrayList<Integer> _posList = _arrEnd.get("chunkInstanceFloatList");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrEnd.put("chunkInstanceFloatList", _posList);
                }
                _posList.add(this._io.pos());
            }
        }
        _attrEnd.put("chunkInstanceFloatList", this._io.pos());
        return this.chunkInstanceFloatList;
    }
    private byte[] sequentialBytesScalar;
    private ArrayList<byte[]> sequentialBytesList;
    private int sequentialIntegerScalar;
    private ArrayList<Integer> sequentialIntegerList;
    private float sequentialFloatScalar;
    private ArrayList<Float> sequentialFloatList;
    private MyEnum sequentialEnumScalar;
    private ArrayList<MyEnum> sequentialEnumList;
    private boolean sequentialBooleanScalar;
    private ArrayList<Boolean> sequentialBooleanList;
    private String sequentialStringScalar;
    private ArrayList<String> sequentialStringList;
    private MyType sequentialStructScalar;
    private ArrayList<MyType> sequentialStructList;
    private Basic _root;
    private KaitaiStruct _parent;
    public byte[] sequentialBytesScalar() { return sequentialBytesScalar; }
    public ArrayList<byte[]> sequentialBytesList() { return sequentialBytesList; }
    public int sequentialIntegerScalar() { return sequentialIntegerScalar; }
    public ArrayList<Integer> sequentialIntegerList() { return sequentialIntegerList; }
    public float sequentialFloatScalar() { return sequentialFloatScalar; }
    public ArrayList<Float> sequentialFloatList() { return sequentialFloatList; }
    public MyEnum sequentialEnumScalar() { return sequentialEnumScalar; }
    public ArrayList<MyEnum> sequentialEnumList() { return sequentialEnumList; }
    public boolean sequentialBooleanScalar() { return sequentialBooleanScalar; }
    public ArrayList<Boolean> sequentialBooleanList() { return sequentialBooleanList; }
    public String sequentialStringScalar() { return sequentialStringScalar; }
    public ArrayList<String> sequentialStringList() { return sequentialStringList; }
    public MyType sequentialStructScalar() { return sequentialStructScalar; }
    public ArrayList<MyType> sequentialStructList() { return sequentialStructList; }
    public Basic _root() { return _root; }
    public KaitaiStruct _parent() { return _parent; }
}
