#!/bin/bash
cd "$(dirname "$(readlink -e "$0")")/dist" || exit 1
exec python -m SimpleHTTPServer 4000
