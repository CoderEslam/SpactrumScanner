package com.doubleclick.spactrumscanner.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doubleclick.spactrumscanner.Repo.Repository
import com.doubleclick.spactrumscanner.ViewModel.ScannerViewModel

/**
 * Created By Eslam Ghazy on 1/3/2023
 */
class ScannerViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ScannerViewModel(repository) as T
    }
}