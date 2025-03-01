package com.pavelmorozov.scannercode.scanner

import androidx.camera.view.PreviewView
import com.pavelmorozov.scannercode.model.Scan

data class ScannerUiState(
    val previewView: PreviewView? = null,
    val scan: Scan? = null,
    val showBottomSheet: Boolean = false,
    val showCameraRequiredDialog: Boolean = false
)
