package dev.axyria.scalastruct

import java.nio.ByteBuffer
import java.nio.ByteOrder
import util.chaining.scalaUtilChainingOps

given unsafeByteKind(using f: UnsafePointerFactory): UnsafeStructKind[Byte] with
  override def alloc(value: Byte): Pointer =
    store(allocEmpty(alignment), 0, value)

  override def allocEmpty(length: Long): Pointer = f.alloc(length)

  override def realloc(address: Pointer, length: Long): Unit =
    f.reallocBlock(address, length)

  override def store(address: Pointer, index: Long, data: Byte): Pointer =
    (address + index).tap(f.realloc[Byte](_, data))

  override def alignment: Int = java.lang.Byte.BYTES

  override def read(addr: Pointer): Byte = f.visit(addr)

given unsafeIntKind(using f: UnsafePointerFactory): UnsafeStructKind[Int] with
  override def alloc(data: Int): Pointer =
    store(allocEmpty(alignment), 0, data)

  override def allocEmpty(length: Long): Pointer =
    f.alloc(length * alignment)

  override def realloc(address: Pointer, length: Long): Unit =
    f.reallocBlock(address, length * alignment)

  override def store(address: Pointer, index: Long, data: Int): Pointer =
    Pointer.buildAddress(address, index, alignment).tap(f.realloc[Int](_, data))

  override def alignment: Int = Integer.BYTES

  override def read(addr: Pointer): Int =
    f.visit[Int](addr)

given unsafeCharKind(using f: UnsafePointerFactory): UnsafeStructKind[Char] with
  override def alloc(data: Char): Pointer =
    store(allocEmpty(alignment), 0, data)

  override def allocEmpty(length: Long): Pointer =
    f.alloc(length * alignment)

  override def realloc(address: Pointer, length: Long): Unit =
    f.reallocBlock(address, length * alignment)

  override def store(address: Pointer, index: Long, data: Char): Pointer =
    Pointer.buildAddress(address, index, alignment).tap(f.realloc[Char](_, data))

  override def alignment: Int = Character.BYTES

  override def read(addr: Pointer): Char =
    f.visit[Char](addr)

given unsafeLongKind(using f: UnsafePointerFactory): UnsafeStructKind[Long] with
  override def alloc(data: Long): Pointer =
    store(allocEmpty(alignment), 0, data)

  override def allocEmpty(length: Long): Pointer =
    f.alloc(length * alignment)

  override def realloc(address: Pointer, length: Long): Unit =
    f.reallocBlock(address, length * alignment)

  override def store(address: Pointer, index: Long, data: Long): Pointer =
    Pointer.buildAddress(address, index, alignment).tap(f.realloc[Long](_, data))

  override def alignment: Int = java.lang.Long.BYTES

  override def read(addr: Pointer): Long =
    f.visit[Long](addr)

given unsafeDoubleKind(using f: UnsafePointerFactory): UnsafeStructKind[Double] with
  override def alloc(data: Double): Pointer =
    store(allocEmpty(alignment), 0, data)

  override def allocEmpty(length: Long): Pointer =
    f.alloc(length * alignment)

  override def realloc(address: Pointer, length: Long): Unit =
    f.reallocBlock(address, length * alignment)

  override def store(address: Pointer, index: Long, data: Double): Pointer =
    Pointer.buildAddress(address, index, alignment).tap(f.realloc[Double](_, data))

  override def alignment: Int = java.lang.Double.BYTES

  override def read(addr: Pointer): Double =
    f.visit[Double](addr)

given unsafeFloatKind(using f: UnsafePointerFactory): UnsafeStructKind[Float] with
  override def alloc(data: Float): Pointer =
    store(allocEmpty(alignment), 0, data)

  override def allocEmpty(length: Long): Pointer =
    f.alloc(length * alignment)

  override def realloc(address: Pointer, length: Long): Unit =
    f.reallocBlock(address, length * alignment)

  override def store(address: Pointer, index: Long, data: Float): Pointer =
    Pointer.buildAddress(address, index, alignment).tap(f.realloc[Float](_, data))

  override def alignment: Int = java.lang.Float.BYTES

  override def read(addr: Pointer): Float =
    f.visit[Float](addr)

given unsafeShortKind(using f: UnsafePointerFactory): UnsafeStructKind[Short] with
  override def alloc(data: Short): Pointer =
    store(allocEmpty(alignment), 0, data)

  override def allocEmpty(length: Long): Pointer =
    f.alloc(length * alignment)

  override def realloc(address: Pointer, length: Long): Unit =
    f.reallocBlock(address, length * alignment)

  override def store(address: Pointer, index: Long, data: Short): Pointer =
    Pointer.buildAddress(address, index, alignment).tap(f.realloc[Short](_, data))

  override def alignment: Int = java.lang.Short.BYTES

  override def read(addr: Pointer): Short =
    f.visit[Short](addr)
