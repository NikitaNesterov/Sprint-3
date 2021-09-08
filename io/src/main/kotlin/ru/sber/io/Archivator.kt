package ru.sber.io

import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */

    fun zipLogFile() {
        var incomeFile = File("io/logfile.log")
        var zipFile = File("io/ZipArchive.zip")

        ZipOutputStream(BufferedOutputStream(FileOutputStream(zipFile))).use { out ->
            if (incomeFile.length() > 1) {
                FileInputStream(incomeFile).use { input ->
                    BufferedInputStream(input).use { origin ->
                        val entry = ZipEntry(incomeFile.name)
                        out.putNextEntry(entry)
                        origin.copyTo(out, 1024)
                    }
                }
            }
        }
    }


    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile() {
        var zipFile = ZipFile("io/ZipArchive.zip")
        var outComeFile = File("io/unzippedLogfile.log")

        zipFile.use { zip ->
            zip.entries().asSequence().forEach { entry ->
                zip.getInputStream(entry).use { input ->
                    BufferedReader(InputStreamReader(input, "ISO-8859-1")).use { reader ->
                        BufferedWriter(FileWriter(outComeFile)).use { writer ->
                            var line: String? = null
                            while ({ line = reader.readLine(); line }() != null) {
                                writer.append(line).append('\n')
                            }
                            writer.flush()
                        }
                    }
                }
            }
        }
    }
}