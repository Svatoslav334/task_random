SUMMARY = "Install Python and shell scripts to /scripts"
# краткое описание
LICENSE = "MIT"
# тип лицензии
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://random_number.py"
# путь до запускаемого файла

S = "${UNPACKDIR}"
# рбочая директория

do_compile() {
    echo '#!/bin/sh' > ${S}/random_chars.sh
    echo 'head /dev/urandom | tr -dc A-Za-z0-9 | head -c 25 ; echo' >> ${S}/random_chars.sh
    chmod +x ${S}/random_chars.sh
}

do_install() {
    install -d ${D}/scripts
# D - корень таргет-системы
    install -m 0755 ${S}/random_number.py ${D}/scripts/random_number.py
    install -m 0755 ${S}/random_chars.sh ${D}/scripts/random_chars.sh
}
FILES:${PN} += "/scripts"

RDEPENDS:${PN} += "python3-core"
