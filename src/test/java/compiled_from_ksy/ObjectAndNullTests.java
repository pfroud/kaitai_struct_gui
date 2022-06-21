// This is a generated file! Please edit source .ksy file and use kaitai-struct-compiler to rebuild

package compiled_from_ksy;

import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.KaitaiStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ObjectAndNullTests extends KaitaiStruct {
    public Map<String, Integer> _attrStart = new HashMap<String, Integer>();
    public Map<String, Integer> _attrEnd = new HashMap<String, Integer>();
    public Map<String, ArrayList<Integer>> _arrStart = new HashMap<String, ArrayList<Integer>>();
    public Map<String, ArrayList<Integer>> _arrEnd = new HashMap<String, ArrayList<Integer>>();

    public static ObjectAndNullTests fromFile(String fileName) throws IOException {
        return new ObjectAndNullTests(new ByteBufferKaitaiStream(fileName));
    }
    public static String[] _seqFields = new String[] { "switchOnThis", "sequentialFieldCompileTimeTypeIsIntAndRuntimeValueIsNull", "sequentialFieldCompileTimeTypeIsObjectAndRuntimeTypeIsInteger", "sequentialFieldCompileTimeTypeIsObjectAndRuntimeValueIsNull", "sequentialFieldListOfObjects" };

    public ObjectAndNullTests(KaitaiStream _io) {
        this(_io, null, null);
    }

    public ObjectAndNullTests(KaitaiStream _io, KaitaiStruct _parent) {
        this(_io, _parent, null);
    }

    public ObjectAndNullTests(KaitaiStream _io, KaitaiStruct _parent, ObjectAndNullTests _root) {
        super(_io);
        this._parent = _parent;
        this._root = _root == null ? this : _root;
        _read();
    }
    private void _read() {
        _attrStart.put("switchOnThis", this._io.pos());
        this.switchOnThis = this._io.readU1();
        _attrEnd.put("switchOnThis", this._io.pos());
        if (false) {
            _attrStart.put("sequentialFieldCompileTimeTypeIsIntAndRuntimeValueIsNull", this._io.pos());
            this.sequentialFieldCompileTimeTypeIsIntAndRuntimeValueIsNull = this._io.readU1();
            _attrEnd.put("sequentialFieldCompileTimeTypeIsIntAndRuntimeValueIsNull", this._io.pos());
        }
        _attrStart.put("sequentialFieldCompileTimeTypeIsObjectAndRuntimeTypeIsInteger", this._io.pos());
        switch (switchOnThis()) {
        case 32: {
            this.sequentialFieldCompileTimeTypeIsObjectAndRuntimeTypeIsInteger = (Object) (this._io.readU1());
            break;
        }
        case 0: {
            this.sequentialFieldCompileTimeTypeIsObjectAndRuntimeTypeIsInteger = (Object) (this._io.readBitsIntBe(1) != 0);
            break;
        }
        }
        _attrEnd.put("sequentialFieldCompileTimeTypeIsObjectAndRuntimeTypeIsInteger", this._io.pos());
        if (false) {
            _attrStart.put("sequentialFieldCompileTimeTypeIsObjectAndRuntimeValueIsNull", this._io.pos());
            switch (switchOnThis()) {
            case 32: {
                this.sequentialFieldCompileTimeTypeIsObjectAndRuntimeValueIsNull = (Object) (this._io.readU1());
                break;
            }
            case 0: {
                this.sequentialFieldCompileTimeTypeIsObjectAndRuntimeValueIsNull = (Object) (this._io.readBitsIntBe(1) != 0);
                break;
            }
            }
            _attrEnd.put("sequentialFieldCompileTimeTypeIsObjectAndRuntimeValueIsNull", this._io.pos());
        }
        _attrStart.put("sequentialFieldListOfObjects", this._io.pos());
        sequentialFieldListOfObjects = new ArrayList<Object>(((Number) (2)).intValue());
        for (int i = 0; i < 2; i++) {
            {
                ArrayList<Integer> _posList = _arrStart.get("sequentialFieldListOfObjects");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrStart.put("sequentialFieldListOfObjects", _posList);
                }
                _posList.add(this._io.pos());
            }
            switch (switchOnThis()) {
            case 32: {
                {
                    ArrayList<Integer> _posList = _arrStart.get("sequentialFieldListOfObjects");
                    if (_posList == null) {
                        _posList = new ArrayList<Integer>();
                        _arrStart.put("sequentialFieldListOfObjects", _posList);
                    }
                    _posList.add(this._io.pos());
                }
                this.sequentialFieldListOfObjects.add((Object) (this._io.readU1()));
                {
                    ArrayList<Integer> _posList = _arrEnd.get("sequentialFieldListOfObjects");
                    if (_posList == null) {
                        _posList = new ArrayList<Integer>();
                        _arrEnd.put("sequentialFieldListOfObjects", _posList);
                    }
                    _posList.add(this._io.pos());
                }
                break;
            }
            case 0: {
                {
                    ArrayList<Integer> _posList = _arrStart.get("sequentialFieldListOfObjects");
                    if (_posList == null) {
                        _posList = new ArrayList<Integer>();
                        _arrStart.put("sequentialFieldListOfObjects", _posList);
                    }
                    _posList.add(this._io.pos());
                }
                this.sequentialFieldListOfObjects.add((Object) (this._io.readBitsIntBe(1) != 0));
                {
                    ArrayList<Integer> _posList = _arrEnd.get("sequentialFieldListOfObjects");
                    if (_posList == null) {
                        _posList = new ArrayList<Integer>();
                        _arrEnd.put("sequentialFieldListOfObjects", _posList);
                    }
                    _posList.add(this._io.pos());
                }
                break;
            }
            }
            {
                ArrayList<Integer> _posList = _arrEnd.get("sequentialFieldListOfObjects");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrEnd.put("sequentialFieldListOfObjects", _posList);
                }
                _posList.add(this._io.pos());
            }
        }
        _attrEnd.put("sequentialFieldListOfObjects", this._io.pos());
    }
    private Integer instanceCompileTimeTypeIsIntAndRuntimeValueIsNull;

    /**
     * This is an instance.
     * The compile-time type (in the Java source code) will be int.
     * The runtime value will be null.
     * The tree node icon should check the compile-time type, not the 
     * runtime type.
     */
    public Integer instanceCompileTimeTypeIsIntAndRuntimeValueIsNull() {
        if (this.instanceCompileTimeTypeIsIntAndRuntimeValueIsNull != null)
            return this.instanceCompileTimeTypeIsIntAndRuntimeValueIsNull;
        if (false) {
            _attrStart.put("instanceCompileTimeTypeIsIntAndRuntimeValueIsNull", this._io.pos());
            this.instanceCompileTimeTypeIsIntAndRuntimeValueIsNull = this._io.readU1();
            _attrEnd.put("instanceCompileTimeTypeIsIntAndRuntimeValueIsNull", this._io.pos());
        }
        return this.instanceCompileTimeTypeIsIntAndRuntimeValueIsNull;
    }
    private Object instanceCompileTimeTypeIsObjectAndRuntimeTypeIsFloat;

    /**
     * This is an instance.
     * The compile-time type (in the Java source code) will be java.lang.Object.
     * The runtime type will be integer.
     * The tree node icon should check the runtime type when the compile-time
     * type is Object.
     */
    public Object instanceCompileTimeTypeIsObjectAndRuntimeTypeIsFloat() {
        if (this.instanceCompileTimeTypeIsObjectAndRuntimeTypeIsFloat != null)
            return this.instanceCompileTimeTypeIsObjectAndRuntimeTypeIsFloat;
        _attrStart.put("instanceCompileTimeTypeIsObjectAndRuntimeTypeIsFloat", this._io.pos());
        switch (switchOnThis()) {
        case 32: {
            this.instanceCompileTimeTypeIsObjectAndRuntimeTypeIsFloat = (Object) (this._io.readU1());
            break;
        }
        case 0: {
            this.instanceCompileTimeTypeIsObjectAndRuntimeTypeIsFloat = (Object) (this._io.readBitsIntBe(1) != 0);
            break;
        }
        }
        _attrEnd.put("instanceCompileTimeTypeIsObjectAndRuntimeTypeIsFloat", this._io.pos());
        return this.instanceCompileTimeTypeIsObjectAndRuntimeTypeIsFloat;
    }
    private Object instanceCompileTimeTypeIsObjectAndRuntimeValueIsNull;

    /**
     * This is an instance.
     * The compile-time type (in the Java source code) will be java.lang.Object.
     * The runtime value will be null.
     * We cannot tell what type this node is.
     */
    public Object instanceCompileTimeTypeIsObjectAndRuntimeValueIsNull() {
        if (this.instanceCompileTimeTypeIsObjectAndRuntimeValueIsNull != null)
            return this.instanceCompileTimeTypeIsObjectAndRuntimeValueIsNull;
        if (false) {
            _attrStart.put("instanceCompileTimeTypeIsObjectAndRuntimeValueIsNull", this._io.pos());
            switch (switchOnThis()) {
            case 32: {
                this.instanceCompileTimeTypeIsObjectAndRuntimeValueIsNull = (Object) (this._io.readU1());
                break;
            }
            case 0: {
                this.instanceCompileTimeTypeIsObjectAndRuntimeValueIsNull = (Object) (this._io.readBitsIntBe(1) != 0);
                break;
            }
            }
            _attrEnd.put("instanceCompileTimeTypeIsObjectAndRuntimeValueIsNull", this._io.pos());
        }
        return this.instanceCompileTimeTypeIsObjectAndRuntimeValueIsNull;
    }
    private ArrayList<Object> instanceListOfObjects;

    /**
     * This is an instance.
     * The compile-time type (in the Java source code) will be ArrayList<java.lang.Object>.
     * The runtime value will be a list of two integers.
     * We do not want to iterate through the list to try to determine what type the contents are,
     * so the icon should use the 'unknown' data type.
     */
    public ArrayList<Object> instanceListOfObjects() {
        if (this.instanceListOfObjects != null)
            return this.instanceListOfObjects;
        _attrStart.put("instanceListOfObjects", this._io.pos());
        instanceListOfObjects = new ArrayList<Object>(((Number) (2)).intValue());
        for (int i = 0; i < 2; i++) {
            {
                ArrayList<Integer> _posList = _arrStart.get("instanceListOfObjects");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrStart.put("instanceListOfObjects", _posList);
                }
                _posList.add(this._io.pos());
            }
            switch (switchOnThis()) {
            case 32: {
                {
                    ArrayList<Integer> _posList = _arrStart.get("instanceListOfObjects");
                    if (_posList == null) {
                        _posList = new ArrayList<Integer>();
                        _arrStart.put("instanceListOfObjects", _posList);
                    }
                    _posList.add(this._io.pos());
                }
                this.instanceListOfObjects.add((Object) (this._io.readU1()));
                {
                    ArrayList<Integer> _posList = _arrEnd.get("instanceListOfObjects");
                    if (_posList == null) {
                        _posList = new ArrayList<Integer>();
                        _arrEnd.put("instanceListOfObjects", _posList);
                    }
                    _posList.add(this._io.pos());
                }
                break;
            }
            case 0: {
                {
                    ArrayList<Integer> _posList = _arrStart.get("instanceListOfObjects");
                    if (_posList == null) {
                        _posList = new ArrayList<Integer>();
                        _arrStart.put("instanceListOfObjects", _posList);
                    }
                    _posList.add(this._io.pos());
                }
                this.instanceListOfObjects.add((Object) (this._io.readBitsIntBe(1) != 0));
                {
                    ArrayList<Integer> _posList = _arrEnd.get("instanceListOfObjects");
                    if (_posList == null) {
                        _posList = new ArrayList<Integer>();
                        _arrEnd.put("instanceListOfObjects", _posList);
                    }
                    _posList.add(this._io.pos());
                }
                break;
            }
            }
            {
                ArrayList<Integer> _posList = _arrEnd.get("instanceListOfObjects");
                if (_posList == null) {
                    _posList = new ArrayList<Integer>();
                    _arrEnd.put("instanceListOfObjects", _posList);
                }
                _posList.add(this._io.pos());
            }
        }
        _attrEnd.put("instanceListOfObjects", this._io.pos());
        return this.instanceListOfObjects;
    }
    private int switchOnThis;
    private Integer sequentialFieldCompileTimeTypeIsIntAndRuntimeValueIsNull;
    private Object sequentialFieldCompileTimeTypeIsObjectAndRuntimeTypeIsInteger;
    private Object sequentialFieldCompileTimeTypeIsObjectAndRuntimeValueIsNull;
    private ArrayList<Object> sequentialFieldListOfObjects;
    private ObjectAndNullTests _root;
    private KaitaiStruct _parent;
    public int switchOnThis() { return switchOnThis; }

    /**
     * This is a sequential field.
     * The compile-time type (in the Java source code) will be int.
     * The runtime value will be null.
     * The tree node icon should check the compile-time type, not the 
     * runtime type.
     */
    public Integer sequentialFieldCompileTimeTypeIsIntAndRuntimeValueIsNull() { return sequentialFieldCompileTimeTypeIsIntAndRuntimeValueIsNull; }

    /**
     * This is a sequential field.
     * The compile-time type (in the Java source code) will be java.lang.Object.
     * The runtime type will be integer.
     * The tree node icon should check the runtime type when the compile-time
     * type is Object.
     */
    public Object sequentialFieldCompileTimeTypeIsObjectAndRuntimeTypeIsInteger() { return sequentialFieldCompileTimeTypeIsObjectAndRuntimeTypeIsInteger; }

    /**
     * This is a sequential field.
     * The compile-time type (in the Java source code) will be java.lang.Object.
     * The runtime value will be null.
     * We cannot tell what type this node is.
     */
    public Object sequentialFieldCompileTimeTypeIsObjectAndRuntimeValueIsNull() { return sequentialFieldCompileTimeTypeIsObjectAndRuntimeValueIsNull; }

    /**
     * This is a sequential field.
     * The compile-time type (in the Java source code) will be ArrayList<java.lang.Object>.
     * The runtime value will be a list of two integers.
     * We do not want to iterate through the list to try to determine what type the contents are,
     * so the icon should use the 'unknown' data type.
     */
    public ArrayList<Object> sequentialFieldListOfObjects() { return sequentialFieldListOfObjects; }
    public ObjectAndNullTests _root() { return _root; }
    public KaitaiStruct _parent() { return _parent; }
}
