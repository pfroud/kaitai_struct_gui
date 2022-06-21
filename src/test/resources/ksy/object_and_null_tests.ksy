meta:
  id: object_and_null_tests
seq:
  - id: switch_on_this
    type: u1
  - id: sequential_field_compile_time_type_is_int_and_runtime_value_is_null
    doc: |
      This is a sequential field.
      The compile-time type (in the Java source code) will be int.
      The runtime value will be null.
      The tree node icon should check the compile-time type, not the 
      runtime type.
    if: false
    type: u1
  - id: sequential_field_compile_time_type_is_object_and_runtime_type_is_integer
    doc: |
      This is a sequential field.
      The compile-time type (in the Java source code) will be java.lang.Object.
      The runtime type will be integer.
      The tree node icon should check the runtime type when the compile-time
      type is Object.
    type:
      switch-on: switch_on_this
      cases:
        # The first byte of ascii.txt is 0x20
        0x20: u1
        0x00: b1
  - id: sequential_field_compile_time_type_is_object_and_runtime_value_is_null
    doc: |
      This is a sequential field.
      The compile-time type (in the Java source code) will be java.lang.Object.
      The runtime value will be null.
      We cannot tell what type this node is.
    if: false
    type:
      switch-on: switch_on_this
      cases:
        # The first byte of ascii.txt is 0x20
        0x20: u1
        0x00: b1
instances:
  instance_compile_time_type_is_int_and_runtime_value_is_null:
    doc: |
      This is an instance.
      The compile-time type (in the Java source code) will be int.
      The runtime value will be null.
      The tree node icon should check the compile-time type, not the 
      runtime type.
    if: false
    type: u1
  instance_compile_time_type_is_object_and_runtime_type_is_float:
    doc: |
      This is an instance.
      The compile-time type (in the Java source code) will be java.lang.Object.
      The runtime type will be integer.
      The tree node icon should check the runtime type when the compile-time
      type is Object.
    type:
      switch-on: switch_on_this
      cases:
        # The first byte of ascii.txt is 0x20
        0x20: u1
        0x00: b1
  instance_compile_time_type_is_object_and_runtime_value_is_null:
    doc: |
      This is an instance.
      The compile-time type (in the Java source code) will be java.lang.Object.
      The runtime value will be null.
      We cannot tell what type this node is.
    if: false
    type:
      switch-on: switch_on_this
      cases:
        # The first byte of ascii.txt is 0x20
        0x20: u1
        0x00: b1