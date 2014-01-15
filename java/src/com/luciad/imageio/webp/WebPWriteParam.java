/*
 * Copyright 2013 Luciad (http://www.luciad.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.luciad.imageio.webp;

import javax.imageio.ImageWriteParam;
import java.util.Locale;

public class WebPWriteParam extends ImageWriteParam {
  static {
    WebP.loadNativeLibrary();
  }

  long fPointer;
  private final int defaultLossless;

  public WebPWriteParam( Locale aLocale ) {
    super( aLocale );
    fPointer = createConfig();
    if ( fPointer == 0 ) {
      throw new OutOfMemoryError();
    }
    defaultLossless = getLossless( fPointer );
    canWriteCompressed = true;
    compressionTypes = new String[]{
        "Lossy",
        "Lossless"
    };
    compressionType = compressionTypes[defaultLossless];
    compressionQuality = getQuality( fPointer ) / 100f;
    compressionMode = MODE_EXPLICIT;
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    deleteConfig( fPointer );
    fPointer = 0L;
  }

  private static native long createConfig();

  private static native void deleteConfig( long aPointer );

  long getPointer() {
    return fPointer;
  }

  @Override
  public float getCompressionQuality() {
    return super.getCompressionQuality();
  }

  @Override
  public void setCompressionQuality( float quality ) {
    super.setCompressionQuality( quality );
    setQuality( fPointer, quality * 100f );
  }

  @Override
  public void setCompressionType( String compressionType ) {
    super.setCompressionType( compressionType );
    for ( int i = 0; i < compressionTypes.length; i++ ) {
      if ( compressionTypes[i].equals( compressionType ) ) {
        setLossless( fPointer, i );
        break;
      }
    }

  }

  @Override
  public void unsetCompression() {
    super.unsetCompression();
    setLossless( fPointer, defaultLossless );
  }

  public int getTargetSize() {
    return getTargetSize( fPointer );
  }

  public void setTargetSize( int aTargetSize ) {
    setTargetSize( fPointer, aTargetSize );
  }

  public float getTargetPSNR() {
    return getTargetPSNR( fPointer );
  }

  public void setTargetPSNR( float aTargetPSNR ) {
    setTargetPSNR( fPointer, aTargetPSNR );
  }

  public int getMethod() {
    return getMethod( fPointer );
  }

  public void setMethod( int aMethod ) {
    setMethod( fPointer, aMethod );
  }

  public int getSegments() {
    return getSegments( fPointer );
  }

  public void setSegments( int aSegments ) {
    setSegments( fPointer, aSegments );
  }

  public int getSnsStrength() {
    return getSnsStrength( fPointer );
  }

  public void setSnsStrength( int aSnsStrength ) {
    setSnsStrength( fPointer, aSnsStrength );
  }

  public int getFilterStrength() {
    return getFilterStrength( fPointer );
  }

  public void setFilterStrength( int aFilterStrength ) {
    setFilterStrength( fPointer, aFilterStrength );
  }

  public int getFilterSharpness() {
    return getFilterSharpness( fPointer );
  }

  public void setFilterSharpness( int aFilterSharpness ) {
    setFilterSharpness( fPointer, aFilterSharpness );
  }

  public int getFilterType() {
    return getFilterType( fPointer );
  }

  public void setFilterType( int aFilterType ) {
    setFilterType( fPointer, aFilterType );
  }

  public int getAutofilter() {
    return getAutofilter( fPointer );
  }

  public void setAutofilter( int aAutofilter ) {
    setAutofilter( fPointer, aAutofilter );
  }

  public int getPass() {
    return getPass( fPointer );
  }

  public void setPass( int aPass ) {
    setPass( fPointer, aPass );
  }

  public int getShowCompressed() {
    return getShowCompressed( fPointer );
  }

  public void setShowCompressed( int aShowCompressed ) {
    setShowCompressed( fPointer, aShowCompressed );
  }

  public int getPreprocessing() {
    return getPreprocessing( fPointer );
  }

  public void setPreprocessing( int aPreprocessing ) {
    setPreprocessing( fPointer, aPreprocessing );
  }

  public int getPartitions() {
    return getPartitions( fPointer );
  }

  public void setPartitions( int aPartitions ) {
    setPartitions( fPointer, aPartitions );
  }

  public int getPartitionLimit() {
    return getPartitionLimit( fPointer );
  }

  public void setPartitionLimit( int aPartitionLimit ) {
    setPartitionLimit( fPointer, aPartitionLimit );
  }

  public int getAlphaCompression() {
    return getAlphaCompression( fPointer );
  }

  public void setAlphaCompression( int aAlphaCompression ) {
    setAlphaCompression( fPointer, aAlphaCompression );
  }

  public int getAlphaFiltering() {
    return getAlphaFiltering( fPointer );
  }

  public void setAlphaFiltering( int aAlphaFiltering ) {
    setAlphaFiltering( fPointer, aAlphaFiltering );
  }

  public int getAlphaQuality() {
    return getAlphaQuality( fPointer );
  }

  public void setAlphaQuality( int aAlphaQuality ) {
    setAlphaQuality( fPointer, aAlphaQuality );
  }

  private static native float getQuality( long aPointer );

  private static native void setQuality( long aPointer, float aQuality );

  private static native int getTargetSize( long aPointer );

  private static native void setTargetSize( long aPointer, int aTargetSize );

  private static native float getTargetPSNR( long aPointer );

  private static native void setTargetPSNR( long aPointer, float aTargetPSNR );

  private static native int getMethod( long aPointer );

  private static native void setMethod( long aPointer, int aMethod );

  private static native int getSegments( long aPointer );

  private static native void setSegments( long aPointer, int aSegments );

  private static native int getSnsStrength( long aPointer );

  private static native void setSnsStrength( long aPointer, int aSnsStrength );

  private static native int getFilterStrength( long aPointer );

  private static native void setFilterStrength( long aPointer, int aFilterStrength );

  private static native int getFilterSharpness( long aPointer );

  private static native void setFilterSharpness( long aPointer, int aFilterSharpness );

  private static native int getFilterType( long aPointer );

  private static native void setFilterType( long aPointer, int aFilterType );

  private static native int getAutofilter( long aPointer );

  private static native void setAutofilter( long aPointer, int aAutofilter );

  private static native int getPass( long aPointer );

  private static native void setPass( long aPointer, int aPass );

  private static native int getShowCompressed( long aPointer );

  private static native void setShowCompressed( long aPointer, int aShowCompressed );

  private static native int getPreprocessing( long aPointer );

  private static native void setPreprocessing( long aPointer, int aPreprocessing );

  private static native int getPartitions( long aPointer );

  private static native void setPartitions( long aPointer, int aPartitions );

  private static native int getPartitionLimit( long aPointer );

  private static native void setPartitionLimit( long aPointer, int aPartitionLimit );

  private static native int getAlphaCompression( long aPointer );

  private static native void setAlphaCompression( long aPointer, int aAlphaCompression );

  private static native int getAlphaFiltering( long aPointer );

  private static native void setAlphaFiltering( long aPointer, int aAlphaFiltering );

  private static native int getAlphaQuality( long aPointer );

  private static native void setAlphaQuality( long aPointer, int aAlphaQuality );

  private static native int getLossless( long aPointer );

  private static native void setLossless( long aPointer, int aLossless );
}